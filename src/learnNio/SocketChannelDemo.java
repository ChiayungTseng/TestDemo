package learnNio;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by robot on 17-9-30.
 */
public class SocketChannelDemo {
    public static void main(String[] args){
        try {
            new SocketChannelDemo().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void connect() throws Exception{
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8080));

    }
}
