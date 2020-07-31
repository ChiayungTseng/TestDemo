package learnNetty4.discard;

import lombok.Data;

import java.io.Serializable;

@Data
public class Msg implements Serializable {
    public String threadId;

    public String value;

    public Msg(String threadId, String value) {
        this.threadId = threadId;
        this.value = value;
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        long end =0;
        for(int i=0;i<20;i++){
            end = System.nanoTime();
            System.out.println(end-start);
            start =end;
        }

    }
}
