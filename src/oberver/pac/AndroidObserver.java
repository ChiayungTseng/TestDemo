package oberver.pac;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by zhengjiarong on 2017/5/16.
 */
public class AndroidObserver extends MessageObserver {

    @Override
    public void updateMsg(MessageObservable mo, MessageBox mb) {
        System.out.println("**************Android"+mb.getMessage());

    }
}
