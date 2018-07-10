package weikeCompetition;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import sun.misc.BASE64Encoder;
import validate.PostThread;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhengjiarong on 2017/7/18.
 */
public class Register implements Runnable{

    public static void main(String[] args) {
//        List<String> nameList=new ArrayList<String>();
//        Integer threadNum=5;
//        ExecutorService executorService= Executors.newFixedThreadPool(threadNum);
//        for(int i=0;i<threadNum;i++){
//            executorService.submit(new Register());
//        }
/*        try {
            new Register().load("1193775217@qq.com");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        new Register().run();
//        try {
//            new Register().play("");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    public String register(String name)  throws  Exception{
        String url="http://xuetang.cnweike.cn/index.php?r=croom/user/CreateUser";
        PostMethod postMethod=new PostMethod(url);
        postMethod.addParameter("user",
                "{\"type\":1,\"nick\":\""+name+"\",\"email\":\""+name+"\",\"password\":\"96e79218965eb72c92a549dd5a330112\",\"grade\":\"8\",\"schoolTypeId\":\"\",\"subject\":\"\",\"area\":\"2\"}");
        postMethod.setRequestHeader("X-Requested-With","XMLHttpRequest");
        postMethod.setRequestHeader("Cookie","PHPSESSID=pdkbqr6uur9p970sgm1mnv43i3;");
        HttpClient httpClient = new HttpClient();
        httpClient.executeMethod(postMethod);
//        String body=postMethod.getResponseBodyAsString();
//        p(body);
        return name;
    }
    public  String load(String name) throws Exception{

        String url="http://mmdasai.cnweike.cn/index.php?r=matchV3/user/login";
        PostMethod postMethod=new PostMethod(url);
        String md5Pwd= "111111";
        String md5Account= name;
        String rsaAccount = JavaScriptEngine.execute(md5Account);
        String rsaPwd= JavaScriptEngine.execute(md5Pwd);
        postMethod.addParameter("account",rsaAccount);
        postMethod.addParameter("pwd",rsaPwd);
        postMethod.addParameter("rememberMe","0");
        postMethod.setRequestHeader("X-Requested-With","XMLHttpRequest");
//        postMethod.setRequestHeader("Cookie","PHPSESSID=pdkbqr6uur9p970sgm1mnv43i3;");
        HttpClient httpClient = new HttpClient();
       /* HostConfiguration hostConfiguration=new HostConfiguration();
        hostConfiguration.setProxy("172.17.39.203",8888);
        httpClient.setHostConfiguration(hostConfiguration);*/
        httpClient.executeMethod(postMethod);
        p(postMethod.getResponseBodyAsString());
        String cookie=postMethod.getResponseHeader("Set-Cookie").getValue();
//        p(cookie);
        return cookie;
    }
    public void vote(String cookie) throws Exception{
        String weikeId ="116973";
        String url="http://mmdasai.cnweike.cn/index.php?r=matchV3/play/vote";
        PostMethod postMethod=new PostMethod(url);;
        postMethod.addParameter("id",weikeId);
        postMethod.addParameter("rememberMe","0");
        postMethod.setRequestHeader("X-Requested-With","XMLHttpRequest");
        postMethod.setRequestHeader("Cookie",cookie);
        HttpClient httpClient = new HttpClient();
        httpClient.executeMethod(postMethod);
//        p(postMethod.getResponseBodyAsString());
        p("");
    }
    public  void play(String cookie) throws Exception{
        String url="http://mmdasai.cnweike.cn/index.php?r=matchV3/play/index&id=83792";
        GetMethod getMethod=new GetMethod(url);
        getMethod.setRequestHeader("Upgrade-Insecure-Requests","1");
//        getMethod.setRequestHeader("Cookie",cookie);
        HttpClient httpClient = new HttpClient();
        HostConfiguration hostConfiguration=new HostConfiguration();
        hostConfiguration.setProxy("171.13.37.140",48686);
        httpClient.setHostConfiguration(hostConfiguration);
        httpClient.executeMethod(getMethod);
        p(getMethod.getResponseBodyAsString());
    }
    public void collect(String cookie) throws Exception{
//        http://mmdasai.cnweike.cn/index.php?r=matchV3/play/collect
        String url="http://mmdasai.cnweike.cn/index.php?r=matchV3/play/collect";
        PostMethod postMethod=new PostMethod(url);;
        postMethod.addParameter("cid","83792");
        postMethod.setRequestHeader("X-Requested-With","XMLHttpRequest");
        postMethod.setRequestHeader("Cookie",cookie);
        HttpClient httpClient = new HttpClient();
        httpClient.executeMethod(postMethod);
    }
    public void addArgueComment(String cookie) throws Exception{
        String url="http://mmdasai.cnweike.cn/index.php?r=matchV3/play/addArgueComment";
        Random random=new Random();
        String[] commentIds=new String[]{"100040","100055"};
        post(url,cookie,new String[][]{
                {"commentId",commentIds[random.nextInt(2)]},
                {"value","1"}
        });
    }
    public void post(String url,String cookie,String[][] parameters) throws Exception{
        PostMethod postMethod=new PostMethod(url);
        for(String[] p:parameters){
            postMethod.addParameter(p[0],p[1]);
        }
        postMethod.setRequestHeader("X-Requested-With","XMLHttpRequest");
        postMethod.setRequestHeader("Cookie",cookie);
        HttpClient httpClient = new HttpClient();
        httpClient.executeMethod(postMethod);
    }
    public  void p(String s){
        System.out.println(s);
    }


    public void run() {
        int count=0;
        while(true&&count<300){
            Random random=new Random();
            Integer number=random.nextInt(1000000000);
            String namNumber="1"+number;
            try {
                String name=namNumber+"@qq.com";
                register(name);
                String cookie=load(name);
                vote(cookie);
                if(random.nextInt(10)<2){
//                    collect(cookie);
                }
                if(random.nextInt(10)<2){
//                    addArgueComment(cookie);
                }
//                play(cookie);
//                nameList.add(name);
                System.out.println(name);
                Integer seconds=random.nextInt(30);
                Thread.sleep(1000*seconds);
                count++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String encoderByMd5(String str) throws Exception{
        //确定计算方法
        MessageDigest md5= MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
}
