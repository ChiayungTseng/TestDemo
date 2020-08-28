package learnLock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {



    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start...");
                LockSupport.park();
                System.out.println("out...");
            }
        });
        LockSupport.unpark(t);
        t.start();
//        Thread.sleep(3000);

    }
}
