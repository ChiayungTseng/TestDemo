package hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by zhengjiarong on 2017/7/7.
 */
public class Map extends MapReduceBase implements Mapper<LongWritable,Text,Text,IntWritable>{
    private Text word=new Text();
    private final static IntWritable one=new IntWritable(1);

    public void map(LongWritable longWritable, Text text, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
        StringTokenizer st=new StringTokenizer(text.toString());
        while(st.hasMoreTokens()){
            word.set(st.nextToken());
            outputCollector.collect(word,one);
        }
    }
}
