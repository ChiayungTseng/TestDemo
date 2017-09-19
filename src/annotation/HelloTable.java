package annotation;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zhengjiarong on 2017/6/6.
 */
@Table
public class HelloTable {
    public static void main(String[] args){
        LinkedBlockingQueue linkedBlockingDeque=new LinkedBlockingQueue();
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();

        LinkedList linkedList=new LinkedList();
        try {
            linkedBlockingDeque.put("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
