package oberver.pac;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by zhengjiarong on 2017/5/16.
 */
public abstract class MessageObserver implements Observer {

    public void update(Observable o, Object arg) {
        if(o instanceof MessageObservable && arg instanceof MessageBox){
            MessageObservable mo=(MessageObservable)o;
            MessageBox mb=(MessageBox)arg;
            updateMsg(mo,mb);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public abstract void updateMsg(MessageObservable mo,MessageBox mb);
}
