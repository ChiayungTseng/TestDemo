package learnNetty4.discard;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhengjiarong on 2017/10/9.
 */
public class DiscardClient {


    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8100"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));
    static Channel channel;

    public static void main(String[] args) throws Exception {
        // Configure SSL.
        final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslCtx = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            if (sslCtx != null) {
                                p.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
                            }
                            p.addLast(new ObjectDecoder(RPCClassResolver.instance));
                            p.addLast(new ObjectEncoder());
                            p.addLast(new DiscardClientHandler());

                        }
                    });

            // Make the connection attempt.
            ChannelFuture f = b.connect(HOST, PORT).sync();
            channel =f.channel();
            DiscardClient client = new DiscardClient();
            client.work("jljj");
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    DiscardClient client = new DiscardClient();
                    for(int i=0;i<10;i++){
                        client.work(i+"-afa");
                        *//*try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*//*
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DiscardClient client = new DiscardClient();
                    for(int i=0;i<10;i++){
                        client.work(i+"-beta");
                        *//*try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*//*
                    }
                }
            }).start();*/
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
    public final Object waiter=new Object();

    public  void work(String msg){
        String  threadId = UUID.randomUUID().toString();
        try{
            synchronized (waiter){
                long start = System.nanoTime();
                responseMap.put(threadId,new RequestDealer(threadId,waiter,null));
                WriteTool.objectWrite(channel,new Msg(threadId,msg));
                RequestDealer response = responseMap.get(threadId);
                if(response.responseValue==null){
                    try {
                        System.out.println(threadId+":等待通知");
                        waiter.wait(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("处理时间为："+(System.nanoTime()-start)+" 纳秒");
                    response = responseMap.get(threadId);
                    if(response!=null){
                        System.out.println("响应结果为："+response.responseValue);
                    }
                }else {
                    if(response!=null){
                        System.out.println("直接响应结果为："+response.responseValue);
                    }
                }

            }
        }finally {
            responseMap.remove(threadId);
        }
    }
    public static Map<String,RequestDealer> responseMap = new ConcurrentHashMap<>();

    public static void response(String msgStr){
        Msg msg = JSONObject.parseObject(msgStr,Msg.class);
        RequestDealer dealer = responseMap.get(msg.threadId);
        if(dealer!=null){
            dealer.responseValue= msg.value;
            synchronized (dealer.waiter){
                dealer.waiter.notify();

            }
        }
    }

}
