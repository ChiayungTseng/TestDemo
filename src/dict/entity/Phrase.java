package dict.entity;

import lombok.Data;

import java.util.List;

@Data
public class Phrase extends Sentence implements SentenceList {


    private List<Sentence> sentenceList;

}
