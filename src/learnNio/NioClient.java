package learnNio;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by robot on 17-9-17.
 */
public class NioClient {
    public static void main(String[] args){
        try {
            connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void connect() throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8080));
    }
}
