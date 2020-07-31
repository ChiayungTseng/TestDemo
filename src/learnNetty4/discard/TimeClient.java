package learnNetty4.discard;

import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by zhengjiarong on 2017/10/9.
 */
public class TimeClient {
    public static void main(String[] args) throws Exception {
        String host = "192.168.1.104";
        int port = Integer.parseInt("443");
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)
            String s = "POST /distributor/getByDomain HTTP/1.1\n" +
                    "Content-Type: text/plain\n" +
                    "User-Agent: PostmanRuntime/7.25.0\n" +
                    "Accept: */*\n" +
                    "Cache-Control: no-cache\n" +
                    "Postman-Token: 8d9faddf-53e1-4a11-a4d0-1968b69f2ebe\n" +
                    "Host: 192.168.1.104:8002\n" +
                    "Accept-Encoding: gzip, deflate, br\n" +
                    "Content-Length: 40\n" +
                    "Cookie: SESSION=ODg1NmQ2MmUtOTUxYS00YTY1LWJkMjAtZDJhYmY4Nzc0NzYw\n" +
                    "Connection: keep-alive\n" +
                    "\n" +
                    "{\n" +
                    "    \"encryptedData\":\"\",\n" +
                    "    \"vi\": \"\"\n" +
                    "}";
            send(f,s);
//            Thread.sleep(10000);
//            s=
//
//            send(f,s);
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
    public static void send(ChannelFuture f,String s){
        ByteBuf byteBuf = Unpooled.wrappedBuffer(s.getBytes());
        f.channel().writeAndFlush(byteBuf);
    }
}
