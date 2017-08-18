package learnElasticSearch;

import com.google.gson.Gson;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by zhengjiarong on 2017/7/31.
 *
 */
public class SingleClient {
    private TransportClient client;
    public  TransportClient getClient() throws Exception{
        if(client==null){
            // on startup
            Settings settings = Settings.builder()
                    .put("cluster.name", "my-application").build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.171.80"), 9300));
        }
        return client;
    }
    public  IndexResponse index(String index,String type,Object object) throws Exception{
        TransportClient client = getClient();
        Gson gson =new Gson();
        String jsonObject=gson.toJson(object);
        IndexResponse response= client.prepareIndex(index,type)
                .setSource(jsonObject, XContentType.JSON)
                .get();
        /*jsonBuilder()
                .startObject()
                .field("user","kimchy")
                .field("postDate",new Date())
                .field("message","trying out Elasticsearch")
                .endObject()*/
// Index name
        String _index = response.getIndex();
// Type name
        String _type = response.getType();
// Document ID (generated or not)
        String _id = response.getId();
// Version (if it's the first time you index this document, you will get: 1)
        long _version = response.getVersion();
// status has stored current instance statement.
        RestStatus status = response.status();
// on shutdown
//        client.close();
        return response;
    }
    public  GetResponse get(String index,String type,String id) throws Exception{
        TransportClient client = getClient();
        GetResponse response = client.prepareGet(index,type,id)
                .setOperationThreaded(false)
                .get();
        return response;
    }
    public void close(){
        if(client!=null){
            client.close();
        }
    }
}
