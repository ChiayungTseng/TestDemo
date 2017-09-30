package learnKafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.net.InetAddress;
import java.util.Properties;

/**
 * Created by robot on 17-9-20.
 */
public class ProducerDemo {
    public static void main(String[] args){

        try {
            produce();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void produce() throws Exception{
        Properties props= new Properties();
        props.put("client.id", InetAddress.getLocalHost().getHostName());
        props.put("bootstrap.servers","192.168.171.81:9092");
        props.put("acks","all");//控制吞吐：0-最高，1-默认，all-最低
        props.put("retries",0);
        props.put("batch.size",16384);
        props.put("linger.ms",1);
        props.put("buffer.memory",33554432);
        props.put("request.timeout.ms",10);
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        Producer<String,String> producer = new KafkaProducer<String,String>(props);
        for(int i = 0;i<100;i++){
            producer.send(new ProducerRecord<String, String>("test",Integer.toString(i),Integer.toString(i)));
            System.out.println(i);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.close();
    }
}
