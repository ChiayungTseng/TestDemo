package learnElasticSearch.domain;

import java.util.List;

/**
 * Created by zhengjiarong on 2017/8/10.
 */
public class Response {
    private String numFound;
    private String start;
    private List<Doc> docs;

    public String getNumFound() {
        return numFound;
    }

    public void setNumFound(String numFound) {
        this.numFound = numFound;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }
}
