package com.example.java;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

/**
 * @auther yangjianwu
 * @since 2022/7/18
 */
public class TestJson {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person{
        private Integer id;
        private String name;
    }

    @Test
    public void escape(){
        Person yang = new Person(1, "yang");
        String yangJsonStr = JSONObject.toJSONString(yang);
        System.out.println(yangJsonStr);
        System.out.println(JSONObject.toJSONString(yangJsonStr));

        System.out.println(JSONObject.toJSONString("yang"));

    }
}
