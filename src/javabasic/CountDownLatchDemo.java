package javabasic;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static CountDownLatch latch =new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        latch.await();
        System.out.println("");
    }
}
