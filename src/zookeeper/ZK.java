package zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

public class ZK {
    public static String connect = "127.0.0.1:2181";

    public static void main(String[] args) throws Exception {
        ZooKeeper zkc = new ZooKeeper(connect, 50000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 收到事件通知后的回调函数（应该是我们自己的事件处理逻辑）
                System.out.println(event.getType() + "---" + event.getPath());

            }
        });
//        zkc.create("/test","dataSet".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        List<String> rootList =  zkc.getChildren("/",false);

//        String[] nodes = rootList.toArray();
        for(String root:rootList){
            System.out.println(root);
        }
    }

}
