package com.example.bingfa;

import com.example.java.TestJson;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @auther yangjianwu
 * @since 2022/7/20
 */

public class TestFuture {

    @Test
    public void test() throws ExecutionException, InterruptedException {

        System.out.println(System.currentTimeMillis());


        System.out.println(System.currentTimeMillis());
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("子任务");
                System.out.println("子任务1");
                return "测试FutureTask获取异步结果";
            }
        });
        Thread sub = new Thread(futureTask);
        sub.start();
        String result = futureTask.get();
        System.out.println(result);
    }

    @Test
    public void test1(){
        Thread aaa = new Thread(() -> {

            System.out.println("1.aaa");
            TestJson.Person person = new TestJson.Person();
        }, "aaa");
        Thread bbb = new Thread(() -> {
            System.out.println("2.bbb");
        }, "bbb");

        aaa.start();
        bbb.start();

        System.out.println("3.ccc");
        System.out.println("4.ddd");
    }
}
