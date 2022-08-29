package com.example.anno;

import java.lang.annotation.*;

/**
 * @auther yangjianwu
 * @since 2022/8/9
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface MyAnno {
    String value();
    String aa();
}
