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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by zhengjiarong on 2017/7/31.
 *
 */
public class SingleClient {
    private TransportClient client;
    public SingleClient() throws Exception{
        Map<String,String> map = new HashMap<String,String>();
//        map.put("cluster.name","my-application");
        client=getClient(map,"192.168.1.107:9300");
    }
    public SingleClient(Map<String,String> settingConfig,String... addressPort) throws Exception{
        client=getClient(settingConfig,addressPort);
    }
    public  TransportClient getClient() throws Exception{
        return client;
    }
    public TransportClient getClient(Map<String,String> settingConfig,String... addressPort) throws Exception{
        if(client==null){
            //settings config
            Settings.Builder builder=Settings.builder();
            for(Map.Entry<String,String> entry:settingConfig.entrySet()){
                builder.put(entry.getKey(),entry.getValue());
            }
            Settings settings=builder.build();
            client = new PreBuiltTransportClient(settings);
            for(String ap:addressPort){
                String[] apArray=ap.split(":");
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(apArray[0]),Integer.valueOf(apArray[1])));
            }
        }
        return client;
    }
    public <T> IndexResponse index(String index,String type,T object) throws Exception{
        Gson gson =new Gson();
        String jsonObject=gson.toJson(object);
        IndexResponse response= client.prepareIndex(index,type)
                .setSource(jsonObject, XContentType.JSON)
                .get();
        return response;
    }
    public <T> void indexList(String index,String type,List<T> docList) throws Exception{
        for(T doc:docList){
            index(index,type,doc);
        }
    }
    public  GetResponse get(String index,String type,String id) throws Exception{
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
