package javabasic;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import learnNetty4.discard.Msg;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.ServerSocketChannel;
import java.util.HashMap;
import java.util.Map;

public class LangTest {
//    private static final Unsafe unsafe = Unsafe.getUnsafe();
    public static void main(String[] args) throws Exception {
//        ReentrantLock lock= new ReentrantLock();
        ServerSocketChannel serverSock =ServerSocketChannel.open();

        RemoteObject remoteObject1 = null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("");
                Boolean b =Thread.interrupted();
                System.out.println("");
            }
        }).start();
        RemoteObject remoteObject2 = null;
        Object o = null;
        System.out.println(o==remoteObject2);
        System.out.println(o.hashCode());
        ByteArrayOutputStream baos= new ByteArrayOutputStream();

        ObjectOutputStream oos= new ObjectOutputStream(baos);
        Msg msg = new Msg("10L","2323");
        RemoteObject remoteObject = new RemoteObject();
        remoteObject.setClassName("RemoteObject");
        remoteObject.setMethodName("write");
        remoteObject.setMsg(msg);
        oos.writeObject(remoteObject);
        oos.flush();
        byte[] bytes =baos.toByteArray();
        ByteInputStream bis= new ByteInputStream();
        bis.setBuf(bytes);
        ObjectInputStream ois =new ObjectInputStream(bis);
        Object readObject =ois.readObject();
        System.out.println("");
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<100;i++){
            map.put(i,i);
        }

    }
}
