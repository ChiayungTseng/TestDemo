package memcachedTest;


import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;

/**
 * Created by zhengjiarong on 2017/5/25.
 */
public class Demo1 {
    public static void main(String[] args){
        new Demo1().connect();
    }



    public void connect(){
        try {
            MemcachedClient memcachedClient=new MemcachedClient(new InetSocketAddress("smemcached01.rmz.gomo.com",11211));
            Map<SocketAddress, Map<String, String>> map=memcachedClient.getStats();
            memcachedClient.get("");
            for(Map.Entry<SocketAddress, Map<String, String>> entry:map.entrySet()){
                Map<String,String> node=entry.getValue();
                for(Map.Entry<String,String> me:node.entrySet()){
                    System.out.println(me.getKey()+":"+me.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
