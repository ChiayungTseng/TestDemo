package learnNio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by robot on 17-9-29.
 */
public class FileChannelDemo {
    public static void main(String[] args){
        try {
            new FileChannelDemo().read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void read() throws Exception{
        RandomAccessFile file=new RandomAccessFile("/home/robot/IdeaProjects/TestDemo/src/learnNio/nio.txt","rw");
        FileChannel channel=file.getChannel();
        ByteChannelDemo.read(channel);
    }

}
