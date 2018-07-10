package weikeCompetition;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;

/**
 * Created by zhengjiarong on 2018/7/5.
 */
public class JavaScriptEngine {
    public static String execute(String source) throws Exception{
        ScriptEngineManager m = new ScriptEngineManager();
//获取JavaScript执行引擎
        ScriptEngine engine = m.getEngineByName("JavaScript");
        engine.put("params",source);
        String strFile = "D:\\Project\\Git\\TestDemo\\src\\weikeCompetition\\weikeScript.js";
        Reader reader = new FileReader(new File(strFile));
        engine.eval(reader);
        String output= (String)engine.get("output");
/*        //使用管道流，将输出流转为输入流
        PipedReader prd = new PipedReader();
        PipedWriter pwt = new PipedWriter(prd);
//设置执行结果内容的输出流
        engine.getContext().setWriter(pwt);
//js文件的路径
        BufferedReader br = new BufferedReader(prd);
        br.close();
        pwt.close();
        prd.close();*/
        return output;
    }
    public static void main(String[] args){
        try{
            System.out.println(execute("111111"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
