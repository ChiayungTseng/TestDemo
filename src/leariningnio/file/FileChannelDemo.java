package leariningnio.file;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhengjiarong on 2017/9/7.
 */
public class FileChannelDemo {
    public static void main(String[] args){
        try {
            readFile("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void readFile(String path) throws Exception{
        RandomAccessFile file=new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\工具板.txt","rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        long read;
        while((read=fileChannel.read(buffer))!=-1){
            buffer.flip();
            while(buffer.hasRemaining()){
                System.out.print((char)buffer.get());
            }
            buffer.clear();
        }
        String hello="***********hello World****************";
        buffer.put(hello.getBytes());
        buffer.flip();
        fileChannel.write(buffer);
        fileChannel.close();
        file.close();

    }
}
