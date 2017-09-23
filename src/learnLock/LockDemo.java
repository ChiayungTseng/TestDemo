package learnLock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhengjiarong on 2017/9/18.
 */
public class LockDemo {
    private final ReentrantLock reentrantLock=new ReentrantLock();
    private final Condition condition=reentrantLock.newCondition();
    public static final AtomicInteger count=new AtomicInteger();
    public void exam(String flag) throws Exception{
        final Lock lock=this.reentrantLock;
        lock.lockInterruptibly();
        System.out.println(count.get());
        try{
            count.getAndIncrement();//计数加一
            while(count.get()<3){//计数超过3再执行逻辑，否则等待唤醒
                condition.await();
                System.out.println(flag+"*****已经唤醒");
            }
            System.out.println(flag+"*****执行");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
