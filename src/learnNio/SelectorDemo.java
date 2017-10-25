package learnNio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
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
        Selector selector=Selector.open();
        int ops=serverSocketChannel.validOps();
        serverSocketChannel.configureBlocking(false);
        int interests=SelectionKey.OP_ACCEPT|SelectionKey.OP_READ|SelectionKey.OP_WRITE;
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel.socket().bind(new InetSocketAddress(8100));
        while(true){
            selector.selectNow();
//            int readyChannels=selector.select();
//            while(readyChannels==0)continue;
            Set<SelectionKey> set=selector.selectedKeys();
            Iterator<SelectionKey> iterator=set.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey=iterator.next();
                System.out.println("~~~");
                if(selectionKey.isAcceptable()){
                    System.out.println("accepting...");
                    ServerSocketChannel channel=(ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel=channel.accept();
                    String message="HTTP/1.1 200 OK\n" +
                            "Date: Fri, 22 May 2009 06:07:21 GMT\n" +
                            "Content-Type: text/html; charset=UTF-8\n" +
                            "\n" +
                            "<html>\n" +
                            "      <head></head>\n" +
                            "      <body>\n" +
                            "            <!--body goes here-->\n" +
                            "      </body>\n" +
                            "</html>";
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
