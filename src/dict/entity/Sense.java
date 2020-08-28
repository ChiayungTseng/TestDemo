package dict.entity;

import lombok.Data;

import java.util.List;


public class Sense extends ZHChar implements SentenceList{

    private String num;//编号1,2,3

    private String collocation;//关联单词串

    private List<Phrase> maybePhrase;

    private String briefExtend;

    public List<Sentence> sentenceList;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCollocation() {
        return collocation;
    }

    public void setCollocation(String collocation) {
        this.collocation = collocation;
    }

    public List<Phrase> getMaybePhrase() {
        return maybePhrase;
    }

    public void setMaybePhrase(List<Phrase> maybePhrase) {
        this.maybePhrase = maybePhrase;
    }

    public String getBriefExtend() {
        return briefExtend;
    }

    public void setBriefExtend(String briefExtend) {
        this.briefExtend = briefExtend;
    }

    @Override
    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }
}
