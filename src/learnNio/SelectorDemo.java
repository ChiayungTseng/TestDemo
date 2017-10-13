package learnNio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by robot on 17-9-29.
 */
public class SelectorDemo {

    public static void main(String[] args){
        try {
            new SelectorDemo().selector();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void selector() throws Exception{
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        Selector selector=Selector.open();
        int ops=serverSocketChannel.validOps();
        serverSocketChannel.configureBlocking(false);
        int interests=SelectionKey.OP_ACCEPT|SelectionKey.OP_READ|SelectionKey.OP_WRITE;
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true){
            int readyChannels=selector.select();
            while(readyChannels==0)continue;
            Set<SelectionKey> set=selector.selectedKeys();
            Iterator<SelectionKey> iterator=set.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey=iterator.next();
                if(selectionKey.isAcceptable()){
                    System.out.println("accepting...");
                    ServerSocketChannel channel=(ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel=channel.accept();
                    String message="welcome";
                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                    byteBuffer.put(message.getBytes());
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                }else{
                    System.out.println("another events...");
                }
                iterator.remove();
            }

        }

    }
}
