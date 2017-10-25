package learnElasticSearch.data;

import learnElasticSearch.domain.book.BookMain;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhengjiarong on 2017/10/25.
 */
public class DataBaseUtil {
    public static<T> List<T> get(String sql,Class<T> classType){
        List<T> objectList = new LinkedList<T>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://192.168.180.15:3306/ggbook_book_main?useUnicode=true&amp;characterEncoding=utf8";
            Connection connection= DriverManager.getConnection(url,"root","jb88");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                T t=classType.newInstance();

                Field[] fields=classType.getDeclaredFields();
                for(Field f:fields){
                    f.setAccessible(true);
                    String fieldName=f.getName();
                    Object o=resultSet.getObject(fieldName);
                    if(o!=null){
                        f.set(t,o);
                    }
                }
                objectList.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  objectList;
    }
    public static void main(String[] args){
        List<BookMain> books=get("select * from book_main limit 10",BookMain.class);

    }

}
