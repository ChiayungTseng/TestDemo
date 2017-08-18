package learnElasticSearch;

import com.google.gson.Gson;
import learnElasticSearch.domain.Doc;
import learnElasticSearch.domain.SolrBookResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * Created by zhengjiarong on 2017/8/10.
 */
public class BookDealer {

    public SearchResponse queryAll(String index) throws Exception{
        QueryBuilder queryBuilder=QueryBuilders.matchAllQuery();
        return query(index,queryBuilder);
    }

    public SearchResponse query(String index,QueryBuilder builder) throws Exception{
        SingleClient client=new SingleClient();
        return client.getClient().prepareSearch(index).setQuery(builder).get();
    }


    public void index() throws Exception{
        String books="460462+454928+454928+454928+417482+209420+209420+209420+209420+209420+209420+209420+465508+462876+449498+449498+449498+449498+462904+462904+183365+449108+449108+449108+449108+449108+445686+445686+445686+445686+395120+395120+395120+395120+392784+395120+395120+392784+392784+392784+395120+437996+437996+437996+437996+437996+438662+438662+438662+462254+438662+462254+438662+462254+462254+438662+438662+462254+449658+330850+449658+330850+449658+330850+330850+449658+330850+330850+449658+400774+458588+400774+458588+458588+400774+400774+458588+458588+400774+271753+271753+271753+271753+271753+271753+271753+348972+348972+447576+439274+439274+439274+439274+439274+449672+449672+449672+449672+449672+462880+462880+462880+423332+423332+423332+423332+423332+423332+423332+423332+423332+423332+423332+423332+252543+315576+252543+315576+315576+252543+252543+315576+252543+315576+315576+252543+252543+315576+214659+214659+214659+214659+214659+1+440201+230893+425368+346388+419856+425400+364536+401796+369482+390670+381832+369488+235473+391112+369580+231930+390658+330384+244083+276181+278724+231918+401868+307464+390554+350456+278790+364480+369516+346278+269368";
        String url="http://solr.group1.book.lan:8080/solr/core4/select?wt=json&indent=true&q=!wordsum:0&fq=is_del:0&fq=book_id:(%s)&start=0&rows=165";
        url=String.format(url,books);
        GetMethod getMethod=new GetMethod(url);
        HttpClient httpClient=new HttpClient();
        httpClient.executeMethod(getMethod);
        String outCome=getMethod.getResponseBodyAsString();
        Gson gson=new Gson();
        SolrBookResponse solrBookResponse = gson.fromJson(outCome, SolrBookResponse.class);
        SingleClient client=new SingleClient();
        for(Doc doc:solrBookResponse.getResponse().getDocs()){
            IndexResponse indexResponse=client.index("book","introduce",doc);
            System.out.println(indexResponse.getId());
        }
//            GetResponse getResponse=client.get("book","introduce",indexResponse.getId());
//            System.out.println(getResponse.getField("detail"));
    }
}
