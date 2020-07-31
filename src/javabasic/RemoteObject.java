package javabasic;

import learnNetty4.discard.Msg;
import lombok.Data;
import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class RemoteObject<T> implements Serializable {

    private static final long valueOffset;

    static {
        try {
            Field field=Unsafe.class.getDeclaredField("theUnsafe");
            //设置为可访问
            field.setAccessible(true);
            //是静态字段,用null来获取Unsafe实例
            Unsafe unsafe=(Unsafe)field.get(null);

            valueOffset = unsafe.objectFieldOffset
                    (RemoteObject.class.getDeclaredField("className"));
            System.out.println("");
        } catch (Exception ex) { throw new Error(ex); }
    }

    private String className;

    private String methodName;

    private T msg;


}
