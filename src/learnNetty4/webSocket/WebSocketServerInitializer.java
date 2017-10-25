package learnNetty4.webSocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;

/**
 * Created by zhengjiarong on 2017/10/18.
 */
public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel>{
    private static final String WEBSOCKET_PATH = "/websocket";
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
                  ChannelPipeline pipeline = ch.pipeline();
                  pipeline.addLast(new HttpServerCodec());
                pipeline.addLast(new HttpObjectAggregator(65536));
                pipeline.addLast(new WebSocketServerCompressionHandler());
                  pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
                 pipeline.addLast(new WebSocketIndexPageHandler(WEBSOCKET_PATH));
                 pipeline.addLast(new WebSocketFrameHandler());




    }
}
