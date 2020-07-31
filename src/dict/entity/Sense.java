package dict.entity;

import lombok.Data;

import java.util.List;

@Data
public class Sense extends ZHChar implements SentenceList{

    private String num;//编号1,2,3

    private String collocation;//关联单词串

    private List<Phrase> maybePhrase;

    private String briefExtend;

    private List<Sentence> sentenceList;

}
