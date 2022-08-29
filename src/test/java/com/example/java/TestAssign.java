package com.example.java;

import com.example.Pojo;
import org.junit.Test;

/**
 * @auther yangjianwu
 * @since 2022/8/10
 */
public class TestAssign {

    @Test
    public void test() {
        Pojo a = new Pojo(1, "a");
        Pojo b = new Pojo(1, "a");
        System.out.println(b.equals(a));
        System.out.println(b);
    }
}
