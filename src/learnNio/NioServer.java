package learnNio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by robot on 17-9-17.
 */
public class NioServer {
    public static void main(String[] args){
        try {
//            server();
            ServerSocket serverSocket=new ServerSocket(8081);
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
            serverSocketChannel.accept();
            System.out.println("*********Accetp******");
            Thread.sleep(5000);
        }

    }
}
