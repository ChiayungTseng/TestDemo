package javabasic;

public class ObjectWaitDemo {
    public static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {

        for (int i=0;i<5;i++){
            final int num = i;
            Thread.sleep(1000);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        new ObjectWaitDemo().reach(num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        Thread.sleep(2000);

        for(int i=0;i<5;i++){
            Thread.sleep(1000);
            synchronized (lock) {
                System.out.println("notify "+i);
                lock.notify();
            }
        }


    }

    public  void reach(int num) throws InterruptedException {
        synchronized (lock){
            System.out.println("reach lock "+num);
            lock.wait();
            System.out.println("release "+num);
        }
    }
}
