package com.example.java;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.MD5;
import org.junit.Test;

import java.util.Arrays;

/**
 * @auther yangjianwu
 * @since 2022/7/14
 */
public class MD5Test {
    @Test
    public void md5(){
        MD5 md5 = new MD5();
        String encode = Base64.encode(md5.digest("11"));
        System.out.println(encode);
    }

    @Test
    public void test(){
        String[] split = " , ".split(",");
        System.out.println(split.length);
        System.out.println(Arrays.asList(split));
    }
}
