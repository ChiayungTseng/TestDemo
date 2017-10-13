package learnNio;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

/**
 * Created by zhengjiarong on 2017/9/30.
 */
public class ByteChannelDemo {
    public static void read(ByteChannel channel) throws Exception{
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
