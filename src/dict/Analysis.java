package dict;

import com.alibaba.fastjson.JSON;
import dict.entity.*;
import lombok.extern.log4j.Log4j2;
import org.apache.avro.data.Json;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Log4j2
public class Analysis {
    public static void main(String[] args)  {
        String text = text();
        final Word  word = new Word();
        Document document =Jsoup.parse(text);
//        log.info(document.toString());
        Element body =document.body();
        Iterator<Element> bodyIterator = children(body);

        Element h2 = bodyIterator.next();
        if(h2==null){
            log.info("no h2"+document.toString());
            return;
        }
        word.setContent(h2.text());
        Element section = bodyIterator.next();
        if(section==null){
            log.info("no section "+document.toString());
            return;
        }
        List<Sense> senseList = new ArrayList<>();
        word.setSenseList(senseList);
        AtomicReference<SentenceList> sentenceNow = new AtomicReference<>();
        AtomicReference<Sense> senseNow = new AtomicReference<>();
        Iterator<Element> sectionIterator = children(section);
        while(sectionIterator.hasNext()){
            Element element =sectionIterator.next();
            if(element.tag().getName().equals("pron")){
                word.setPronunciation(element.text());
            }
            tagAttrClassMake(element,"span","class",e->word.setClazz(e.text()));
            tagAttrClassMake(element, "div","sense", e->{
                Sense sense = new Sense();
                List<Sentence> sentenceList = new ArrayList<>();
                sense.setSentenceList(sentenceList);
                senseList.add(sense);
                senseNow.set(sense);
                sentenceNow.set(sense);
                Iterator<Element> senseIterator = children(e);
                while(senseIterator.hasNext()){
                    Element senseElement =senseIterator.next();
                    tagAttrClassMake(senseElement,"b","num",s->sense.setNum(s.text()));
                    tagAttrClassMake(senseElement,"span","collocation",s->sense.setCollocation(s.text()));
                    tagAttrClassMake(senseElement,"strong","brief_ex",s->sense.setBriefExtend(s.text()));
                    tagAttrClassMake(senseElement,"span","zh",s->sense.setZh(s.text()));
                }
            });
            tagAttrClassMake(element,"p","ex",s->{
                List<Sentence> sentenceList = sentenceNow.get().getSentenceList();
                Sentence sentence = new Sentence();
                sentenceList.add(buildPhrase(s,sentence));
            });

            tagAttrClassMake(element,"div","maybe_phrase",s->{
                Sense sense = senseNow.get();
                List<Phrase> maybePhraseList = sense.getMaybePhrase();
                if(maybePhraseList==null){
                    maybePhraseList = new ArrayList<>();
                    sense.setMaybePhrase(maybePhraseList);
                }
                Phrase maybePhrase = new Phrase();
                maybePhrase.setSentenceList(new ArrayList<Sentence>());
                maybePhraseList.add(maybePhrase);
                sentenceNow.set(maybePhrase);
                for(Element me:s.children()){
                    tagAttrClassMake(me,"span","mphr_en to",ex->maybePhrase.setContent(me.text()));
                    tagAttrClassMake(me,"span","zh",ex->maybePhrase.setZh(me.text()));
                }
            });

            tagAttrClassMake(element,"div","idom",e->{
                List<Phrase> idiomList = new ArrayList<>();
                word.setIdiomList(idiomList);
                AtomicReference<Phrase> idiomAtomicReference = new AtomicReference<>();
                for(Element senseElement:e.children()){
                    tagAttrClassMake(senseElement,"div","sense",se->{
                        Phrase idiom = buildPhrase(se,new Phrase());
                        idiom.setSentenceList(new ArrayList<>());
                        idiomList.add(idiom);
                        idiomAtomicReference.set(idiom);
                    });
                    tagAttrClassMake(senseElement,"p","ex",se->{
                        List<Sentence> sentenceList = idiomAtomicReference.get().getSentenceList();
                        Sentence sentence = new Sentence();
                        sentenceList.add(buildPhrase(se,sentence));

                    });
                }
            });


        }
        log.info(JSON.toJSONString(word));
    }
    private static <T extends Sentence>T buildPhrase(Element element,final T sentence){
        List<Node> nodeList = element.childNodes();
        for(Node node:nodeList){
            if(node instanceof TextNode){
                sentence.setContent(((TextNode) node).text());
            }else if(node instanceof Element){
                Element zh = (Element)node;
                tagAttrClassMake(zh,"span","zh",ex->sentence.setZh(zh.text()));
            }
        }
        return sentence;
    }

    private static void tagAttrClassMake(Element element,String tagName,String attValue,TagCallback callback){
        tagAttrMake(element,tagName,"class",attValue,callback);
    }

    private static void tagAttrMake(Element element,String tagName,String attrKey,String attValue,TagCallback callback){
        if(element.tag().getName().equals(tagName)){
            for(Attribute attribute:element.attributes()){
                if(attribute.getKey().equals(attrKey)&&attribute.getValue().equals(attValue)){
                    callback.call(element);
                    break;
                }
            }
        }

    }

    private static String nextText(Iterator<Element> iterator){
        return iterator.next().text();
    }

    private static Iterator<Element> children(Element element){
        return element.children().iterator();
    }

    private static String text(){
        return "<link href=\"ncecd.css\" rel=\"stylesheet\"/><h2 class=\"header\">abandon¹</h2><section class=\"ncecd_con\"><pron>/əˈbændən/</pron><span class=\"class\">vt.</span class><div class=\"sense\"><b class=\"num\">1.</b><span class=\"collocation\">[+ child, spouse, family]</span collocation><span class=\"zh\">抛弃；遗弃；离弃</span></div><p class=\"ex\">He abandoned his family.<span class=\"zh\">他遗弃了家人。</span></p><p class=\"ex\">He claimed that his parents had abandoned him.<span class=\"zh\">他声称父母抛弃了他。</span></p><div class=\"maybe_phrase\"><span class=\"mphr_en to\">to abandon sb to their fate</span mphr_en to><span class=\"zh\">让某人听天由命</span></div maybe_phrase><p class=\"ex\">The United States must soldier on, for to abandon Iraq to its fate now would be irresponsible.<span class=\"zh\">美国必须撑下去, 因为现在抛弃伊拉克使之听天由命, 那就是不负责任。</span></p><div class=\"maybe_phrase\"><span class=\"mphr_en to\">to abandon yourself to your fate</span mphr_en to><span class=\"zh\">听天由命</span></div maybe_phrase><p class=\"ex\">She abandoned herself to her fate with a kind of dedication.<span class=\"zh\">她有点执意要自暴自弃。</span></p><div class=\"sense\"><b class=\"num\">2.</b><span class=\"collocation\">[+ vehicle]</span collocation><span class=\"zh\">不得已舍弃；放弃</span></div><p class=\"ex\">He abandoned his car on the motorway.<span class=\"zh\">他把汽车丢弃在高速公路上。</span></p><div class=\"sense\"><b class=\"num\">3.</b><span class=\"collocation\">[+ home, building]</span collocation><span class=\"zh\">离开；离弃</span></div><p class=\"ex\">Others had departed in a hurry, hastily abandoning homes and equipment.<span class=\"zh\">其他人已匆匆离开, 抛弃了家园和家里的设备。</span></p><div class=\"sense\"><b class=\"num\">4.</b><span class=\"collocation\">[+ studies, search, attempt]</span collocation><span class=\"zh\">中止；放弃</span></div><p class=\"ex\">He abandoned his studies after two years.<span class=\"zh\">他两年后放弃了学业。</span></p><p class=\"ex\">Police have abandoned the search for the body.<span class=\"zh\">警方已放弃搜寻尸体。</span></p><div class=\"sense\"><b class=\"num\">5.</b><span class=\"collocation\">[+ idea, policy, plan, claim]</span collocation><span class=\"zh\">完全放弃</span></div><p class=\"ex\">We'd better abandon that idea, there are too many problems.<span class=\"zh\">我们最好放弃那个想法, 问题太多。</span></p><p class=\"ex\">The scheme's investors decided to abandon the project.<span class=\"zh\">项目投资者决定放弃该工程。</span></p><div class=\"maybe_phrase\"><span class=\"mphr_en or\">to abandon all pretence (at/of sth)<span class=\"or\">or</span or>to abandon any pretence (at/of sth)</span mphr_en or><span class=\"zh\">不再假装…；停止伪装…</span></div maybe_phrase><p class=\"ex\">Both countries have abandoned all pretence at disarmament.<span class=\"zh\">两国均不再假装裁军。</span></p><div class=\"sense\"><b class=\"num\">6.</b><strong class=\"brief_ex\">(for insurance purposes)</strong brief_ex><span class=\"zh\">将(受损被保财产)交承保人申请全额赔付</span></div><div class=\"idom\"><span class=\"idom\">idiom</span idom><div class=\"sense\"><span class=\"dodo\">◆</span dodo>to abandon (all) hope<span class=\"zh\">放弃希望；不再抱希望</span></div><p class=\"ex\">On the weekend, rescuers abandoned all hope of finding him alive.<span class=\"zh\">时至周末, 救援人员对活着找到他已不抱任何希望。</span></p><div class=\"sense\"><span class=\"dodo\">◆</span dodo>to abandon ship<span class=\"zh\">弃船</span></div><p class=\"ex\">The officers and crew prepared to abandon ship.<span class=\"zh\">军官和水兵们准备弃船。</span></p><div class=\"sense\"><span class=\"dodo\">◆</span dodo>to abandon yourself to sth<span class=\"label\">&lt;文&gt;</span><span class=\"collocation\">[+ feelings]</span collocation><span class=\"zh\">陷入, 沉湎于(某种情感)</span></div><p class=\"ex\">We were afraid to abandon ourselves to our feelings.<span class=\"zh\">我们不敢放纵自己的情感。</span></p></div idom></section>\n";
    }

}
