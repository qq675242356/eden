package com.example.bingfa;

import com.example.Pojo;

import java.util.concurrent.*;

/**
 * @auther yangjianwu
 * @since 2022/8/2
 */
public class TestCallableAndFuture {

    public static ExecutorService executorService = new ThreadPoolExecutor(4, 40,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), new ThreadPoolExecutor.AbortPolicy());


    static class MyCallable implements Callable<Pojo> {
        @Override
        public Pojo call() throws Exception {
            return new Pojo(1,"yang");
        }
    }

    public static void main(String[] args) {
        Future<Pojo> future = executorService.submit(new MyCallable());
        try {
            System.out.println(future.get());
            System.out.println(future.get());
        } catch (Exception e) {
            // nodo
        } finally {
            executorService.shutdown();
        }
    }
}
