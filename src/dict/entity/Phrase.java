package dict.entity;

import lombok.Data;

import java.util.List;

@Data
public class Phrase extends Sentence implements SentenceList {


    private List<Sentence> sentenceList;

    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }
}
