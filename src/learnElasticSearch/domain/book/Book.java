package learnElasticSearch.domain.book;

import java.io.Serializable;

/**
 * Created by robot on 17-8-19.
 */
public class Book implements Serializable{
    private Integer bookid;
    private String bookname;
    private String author;
    private Integer pid;
    private String pname;
    private Integer newest;
    private String content;

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getNewest() {
        return newest;
    }

    public void setNewest(Integer newest) {
        this.newest = newest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
