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
        ByteBuffer buffer=ByteBuffer.allocate(40);
        int byteRead=channel.read(buffer);
        while(byteRead!=-1){
            System.out.println(byteRead);
            buffer.flip();
            byte[] bytes=new byte[buffer.limit()];
            for(int i=0;i<bytes.length;i++){
                bytes[i]=buffer.get();
            }
            System.out.println(new String(bytes,"utf-8"));
            buffer.clear();
            byteRead=channel.read(buffer);
        }
    }
}
