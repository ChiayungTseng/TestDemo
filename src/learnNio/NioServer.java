package learnNio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by robot on 17-9-17.
 */
public class NioServer {
    public static void main(String[] args){
        try {
//            server();
            ServerSocket serverSocket=new ServerSocket(8100);
            while(true){
                serverSocket.accept();
                System.out.println("*********Accetp******");
                Thread.sleep(10000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void server() throws Exception{
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8081));
        while(true){
            SocketChannel socketChannel=serverSocketChannel.accept();
            System.out.println("*********Accetp******");
            String message="welcome";
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            byteBuffer.put(message.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            socketChannel.finishConnect();
//            Thread.sleep(5000);
        }

    }
}
