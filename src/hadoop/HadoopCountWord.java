package hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Created by zhengjiarong on 2017/7/7.
 */
public class HadoopCountWord {
    public static void main(String[] args) throws Exception{
        JobConf conf=new JobConf(HadoopCountWord.class);
        conf.setJobName("wordcount");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass(Map.class);
        conf.setCombinerClass(Reduce.class);
        conf.setReducerClass(Reduce.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf,new Path("D:\\Users\\Administrator\\Workspaces\\MyEclipse Professional 2014\\TestDemo\\resources\\input"));
        FileOutputFormat.setOutputPath(conf,new Path("D:\\Users\\Administrator\\Workspaces\\MyEclipse Professional 2014\\TestDemo\\resources\\output"));

        JobClient.runJob(conf);

    }



}
