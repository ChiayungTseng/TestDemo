package learnNio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AioDemo {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(7777));
        while(true){
            Future<AsynchronousSocketChannel> future= listener.accept();
            System.out.println("sjfldj");
            future.get();
        }

    }

/*    public static void main(String[] args) throws IOException {
        ServerSocketChannel asynchronousServerSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(7777));
        while(true){
            asynchronousServerSocketChannel.accept();
            System.out.println("sjfldj");
        }

    }*/
}
