package oberver.pac;

import java.util.Observable;

/**
 * Created by zhengjiarong on 2017/5/16.
 */
public class MessageObservable extends Observable {
    public void setMessageBox(MessageBox mb){
        setChanged();
        notifyObservers(mb);
    }

}
