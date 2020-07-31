package learnNetty4.discard;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

public class WriteTool {

    public static void lineBaseWrite(Channel channel, String flagId, String msg){
        String s = JSON.toJSONString(new Msg(flagId,msg))+"\r\n";
        ByteBuf byteBuf = Unpooled.wrappedBuffer(s.getBytes());
        channel.writeAndFlush(byteBuf);
    }
    public static void objectWrite(Channel channel, Object msg){
        channel.writeAndFlush(msg);
    }
}
