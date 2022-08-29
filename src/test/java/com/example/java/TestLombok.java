package com.example.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.Future;

/**
 * @auther yangjianwu
 * @since 2022/8/3
 */
public class TestLombok {

    @Test
    public void test(){
        IndexDTO indexDTO = new IndexDTO(1, 2, 3);
        System.out.println(indexDTO);

        Page<Object> objectPage = new Page<>();
        System.out.println(objectPage);
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IndexDTO {
        private Integer f2;
        private Integer f1;
        private Integer f3;
    }


    @Data
    public static class Page<T> extends ArrayList{

        private int total;

        public Page(){
            super();
            total = 10;
            System.out.println("");
        }
    }
}
