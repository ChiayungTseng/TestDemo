package learnNetty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by robot on 17-9-9.
 */
public class ClientDemo {
    public static void main(String[] args){
        clientConnect();
    }
    public static void clientConnect(){
        ClientBootstrap clientBootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()
                )
        );
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new HelloHanlder());
            }
        });
        ChannelFuture channelFuture=clientBootstrap.connect(new InetSocketAddress("192.168.1.107",8000));
        channelFuture.getChannel();
        System.out.println("hello!!!!!");
    }
    private static class HelloHanlder extends SimpleChannelHandler{
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("client connect.....");
            super.channelConnected(ctx, e);
        }
    }
}
