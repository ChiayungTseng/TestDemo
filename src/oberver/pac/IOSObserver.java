package oberver.pac;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by zhengjiarong on 2017/5/16.
 */
public class IOSObserver extends MessageObserver implements  Runnable{
    public boolean flag=true;
    public String mes="";
    public Thread t=new Thread(this);
    @Override
    public void updateMsg(MessageObservable mo, MessageBox mb) {
        mes=mb.getMessage();
        if(!t.isAlive())t.start();
    }

    @Override
    public void run() {
        while(flag){
            System.out.println("IOS:"+mes);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
