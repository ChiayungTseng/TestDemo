package oberver.pac;

/**
 * Created by zhengjiarong on 2017/5/16.
 */
public class OberverDemo {
    public static void main(String[] args){
        MessageObservable mo=new MessageObservable();
        mo.addObserver(new IOSObserver());
        mo.addObserver(new AndroidObserver());
        MessageBox mb=new MessageBox("all machine prepare");
        mo.setMessageBox(mb);
    }
}
