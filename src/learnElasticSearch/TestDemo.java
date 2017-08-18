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
            BookDealer bookDealer=new BookDealer();
            SearchResponse searchResponse=bookDealer.queryAll("book");
            SearchHit[] searchHitArray=searchResponse.getHits().getHits();
            for(SearchHit searchHit:searchHitArray){
                SearchHitField searchHitField=searchHit.getField("detail");
                Map<String,Object> map=searchHit.getSourceAsMap();
//                searchHitField.getValues();
                System.out.print("");
            }
            QueryBuilder queryBuilder = QueryBuilders.termQuery("book_id","454928");
            searchResponse=bookDealer.query("book",queryBuilder);
            searchHitArray=searchResponse.getHits().getHits();
            for(SearchHit searchHit:searchHitArray){
                SearchHitField searchHitField=searchHit.getField("");
                Map<String,Object> map=searchHit.getSourceAsMap();
                System.out.print("");
//                searchHitField.
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
