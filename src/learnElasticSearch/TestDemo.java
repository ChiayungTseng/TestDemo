package learnElasticSearch;

import com.google.gson.Gson;
import learnElasticSearch.domain.Doc;
import learnElasticSearch.domain.SolrBookResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;

import java.util.Map;

/**
 * Created by zhengjiarong on 2017/7/31.
 */
public class TestDemo {
    public  static void main(String[] args){
        try {
/*            Integer[] bookIds=new Integer[]{103954,104458,104868,105106,105409,106281,106899,107842,107843,108985,109023,109256,109439,109457,109507,109784};
            String[] bookArray=new String[bookIds.length];
            for(int i=0;i<bookIds.length;i++){
                bookArray[i]=String.valueOf(bookIds[i]);
            }
            BookDealer bookDealer=new BookDealer();
            bookDealer.indexBookContent(bookArray);*/
//            search();
            int n=4;
//            n = n >>> 30;
            System.out.println(Integer.toBinaryString(n));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void search() throws Exception{
        BookDealer bookDealer=new BookDealer();
        SearchResponse searchResponse=bookDealer.queryAll("book");
        SearchHit[] searchHitArray=searchResponse.getHits().getHits();
/*        for(SearchHit searchHit:searchHitArray){
            SearchHitField searchHitField=searchHit.getField("source");
            Map<String,Object> map=searchHit.getSourceAsMap();
//                searchHitField.getValues();
            System.out.print("");
        }*/
//        QueryBuilder queryBuilder = QueryBuilders.termQuery("bookid",464022);
        QueryBuilder queryBuilder =QueryBuilders.multiMatchQuery("梵心宗和莲花宗","bookname","author","pname","content");
//        QueryBuilder queryBuilder =QueryBuilders.matchPhraseQuery("content","漂亮健美");
        searchResponse=bookDealer.query("book",queryBuilder);
        searchHitArray=searchResponse.getHits().getHits();
        for(SearchHit searchHit:searchHitArray){
            SearchHitField searchHitField=searchHit.getField("source");
            Map<String,Object> map=searchHit.getSourceAsMap();
            System.out.println(map.get("content"));
            System.out.println("******************************");
//                searchHitField.
        }
        System.out.println();

    }


}
