package learnElasticSearch.domain;

/**
 * Created by zhengjiarong on 2017/8/10.
 */
public class ResponseHeader {
    private String status;
    private String QTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQTime() {
        return QTime;
    }

    public void setQTime(String QTime) {
        this.QTime = QTime;
    }
}
