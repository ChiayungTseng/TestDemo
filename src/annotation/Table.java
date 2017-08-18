package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by zhengjiarong on 2017/6/6.
 */

public @interface Table {
    public String className() default "table";
}
