package learnLock;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zhengjiarong on 2017/9/18.
 */
public class LockMainDemo {
    public static void main(String[] args){
//        LinkedBlockingQueue<String> linkedBlockingQueue=new LinkedBlockingQueue<String>();
        Map<String,String> map=new HashMap<String,String> ();

        String a="abc";
        map.put(a,"dd");
        int i=a.hashCode();
        String six=Integer.toBinaryString(i);
        int o=i >>> 16;
        String six0=Integer.toBinaryString(o);
//        linkedBlockingQueue.add("String");
        LinkedList<String> linkedList=new LinkedList<String>();
        linkedList.add("");
        final LockDemo lockDemo=new LockDemo();
        Thread thread1=new Thread(new Runnable() {
            public void run() {
                try {
                    lockDemo.exam("线程1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2=new Thread(new Runnable() {
            public void run() {
                try {
                    lockDemo.exam("线程2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread3=new Thread(new Runnable() {
            public void run() {
                try {
                    lockDemo.exam("线程3");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread4=new Thread(new Runnable() {
            public void run() {
                try {
                    lockDemo.exam("线程4");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            thread1.start();
            Thread.sleep(2000);
            thread2.start();
            Thread.sleep(2000);
            thread3.start();
            Thread.sleep(2000);
            thread4.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
