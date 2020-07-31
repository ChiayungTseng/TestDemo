package dict.entity;

import lombok.Data;

import java.util.List;

@Data
public class Word {

    private String content;

    private String pronunciation;//发音

    private String clazz;//词类

    private List<Sense> senseList; //不同的意思

    private List<Phrase> idiomList;//习语


}
