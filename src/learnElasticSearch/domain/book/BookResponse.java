package learnElasticSearch.domain.book;

import java.util.Date;
import java.util.List;

/**
 * Created by robot on 17-8-19.
 */
public class BookResponse {
    private Integer alltotal_chapter;
    private Date server_time;
    private List<String> jsonList;

    public Integer getAlltotal_chapter() {
        return alltotal_chapter;
    }

    public void setAlltotal_chapter(Integer alltotal_chapter) {
        this.alltotal_chapter = alltotal_chapter;
    }

    public Date getServer_time() {
        return server_time;
    }

    public void setServer_time(Date server_time) {
        this.server_time = server_time;
    }

    public List<String> getJsonList() {
        return jsonList;
    }

    public void setJsonList(List<String> jsonList) {
        this.jsonList = jsonList;
    }
}
