package com.example.bingfa;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @auther yangjianwu
 * @since 2022/7/15
 */
public class TestExecutor {


    @Test
    public void test(){
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(()->{});
        Future<String> submit = executorService.submit(() -> "a");

        try {
            submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
