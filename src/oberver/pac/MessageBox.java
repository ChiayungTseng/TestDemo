package oberver.pac;

import java.util.Date;

/**
 * Created by zhengjiarong on 2017/5/16.
 */
public class MessageBox {
    public Date updateTime;
    public String message;
    public MessageBox(String _message){
        updateTime=new Date();
        message=_message;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
