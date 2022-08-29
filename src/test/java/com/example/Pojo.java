package com.example;

import com.alibaba.fastjson.JSONObject;
import com.example.anno.MyAnno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

/**
 * @auther yangjianwu
 * @since 2022/7/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pojo {


    private int id;

    @MyAnno(value = "yang",aa = "aaaaa")
    private String name;

    public static void main(String[] args) {
        Pojo yang1 = new Pojo(1, "yang");
        Field[] declaredFields = yang1.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            MyAnno annotation = declaredField.getAnnotation(MyAnno.class);
            System.out.println(annotation);
        }

        String yang = JSONObject.toJSONString(yang1);
        System.out.println(yang);
    }
}
