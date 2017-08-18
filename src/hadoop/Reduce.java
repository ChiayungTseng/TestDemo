package hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by zhengjiarong on 2017/7/7.
 */
public class Reduce extends MapReduceBase implements Reducer<Text,IntWritable,Text,IntWritable>{
    public void reduce(Text key, Iterator<IntWritable> value, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
        int sum=0;
        while(value.hasNext()){
            sum+=value.next().get();
        }
        outputCollector.collect(key,new IntWritable(sum));
    }


}
