package learnElasticSearch;

import com.google.gson.Gson;
import learnElasticSearch.data.DataBaseUtil;
import learnElasticSearch.domain.Doc;
import learnElasticSearch.domain.SolrBookResponse;
import learnElasticSearch.domain.book.Book;
import learnElasticSearch.domain.book.BookMain;
import learnElasticSearch.domain.book.BookResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjiarong on 2017/8/10.
 */
public class BookDealer {
    private SingleClient client;
    public BookDealer() throws Exception{
        client=new SingleClient();
    }

    public SearchResponse queryAll(String index) throws Exception{
        QueryBuilder queryBuilder=QueryBuilders.matchAllQuery();
        return query(index,queryBuilder);
    }
    public SearchResponse query(String index,QueryBuilder builder) throws Exception{
        return query(index,builder,50);
    }
    public SearchResponse query(String index,QueryBuilder builder,int size) throws Exception{
        TransportClient transportClient=client.getClient();
        return transportClient.prepareSearch(index).setQuery(builder).setSize(size).get();
    }

    public void index() throws Exception{
        String books="460462+454928+454928+454928+417482+209420+209420+209420+209420+209420+209420+209420+465508+462876+449498+449498+449498+449498+462904+462904+183365+449108+449108+449108+449108+449108+445686+445686+445686+445686+395120+395120+395120+395120+392784+395120+395120+392784+392784+392784+395120+437996+437996+437996+437996+437996+438662+438662+438662+462254+438662+462254+438662+462254+462254+438662+438662+462254+449658+330850+449658+330850+449658+330850+330850+449658+330850+330850+449658+400774+458588+400774+458588+458588+400774+400774+458588+458588+400774+271753+271753+271753+271753+271753+271753+271753+348972+348972+447576+439274+439274+439274+439274+439274+449672+449672+449672+449672+449672+462880+462880+462880+423332+423332+423332+423332+423332+423332+423332+423332+423332+423332+423332+423332+252543+315576+252543+315576+315576+252543+252543+315576+252543+315576+315576+252543+252543+315576+214659+214659+214659+214659+214659+1+440201+230893+425368+346388+419856+425400+364536+401796+369482+390670+381832+369488+235473+391112+369580+231930+390658+330384+244083+276181+278724+231918+401868+307464+390554+350456+278790+364480+369516+346278+269368";
        String url="http://solr.group1.book.lan:8080/solr/core4/select?wt=json&indent=true&q=!wordsum:0&fq=is_del:0&fq=book_id:(%s)&start=0&rows=165";
        url=String.format(url,books);
        SolrBookResponse solrBookResponse = getObject(url,SolrBookResponse.class);
        SingleClient client=new SingleClient();
        client.indexList("book","introduce",solrBookResponse.getResponse().getDocs());
    }
    public void indexFromDataBase() throws Exception{
        List<BookMain> bookMainList= DataBaseUtil.get("select * from book_main",BookMain.class);
        SingleClient client=new SingleClient();
        client.indexList("book","bookMain",bookMainList);
    }
    public void indexBookContent(String... bookIds) throws Exception{
        for(String bookId:bookIds){
            indexBookContent(bookId);
        }
    }
    public void indexBookContent(String bookId) throws Exception{
        List<Book> bookList=new ArrayList<Book>();
        String ourl="https://pay.3g.cn/book60/WeChat/Book/read?bookid=%s&p=%s&gg=201544259";
        for(int i=0;i<10;i++){
            String url=String.format(ourl,bookId,i+"");
            BookResponse bookResponse = getObject(url,BookResponse.class);
            List<String> bookJsonList=bookResponse.getJsonList();
            for(String bookJson:bookJsonList){
                Gson gson=new Gson();
                Book book=gson.fromJson(bookJson,Book.class);
                bookList.add(book);
            }

        }
        SingleClient client=new SingleClient();
        client.indexList("book","read",bookList);
        System.out.println("async book:"+bookId+"  complete");
    }
    public <T> T getObject(String url,Class<T> t) throws Exception{
        GetMethod getMethod=new GetMethod(url);
        HttpClient httpClient=new HttpClient();
        httpClient.executeMethod(getMethod);
        String outCome= new String(getMethod.getResponseBody(),"utf-8");
        Gson gson=new Gson();
        return gson.fromJson(outCome, t);
    }



}
