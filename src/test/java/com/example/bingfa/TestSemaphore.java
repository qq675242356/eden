package com.example.bingfa;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @auther yangjianwu
 * @since 2022/7/18
 */
public class TestSemaphore {

    Semaphore s;

    @Before
    public void before(){
        s = new Semaphore(10);
    }

    @Test
    public void test(){
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    if(s.availablePermits()==0){
                        System.out.println(Thread.currentThread().getName() + "车位不足");
                    }
                    System.out.println(Thread.currentThread().getName() + "开始停车");
                    s.acquire();
                    int i1 = new Random().nextInt(10);

                    System.out.println(Thread.currentThread().getName() + "停" + i1 + "秒" );
                    TimeUnit.SECONDS.sleep(i1);
                    s.release();
                    System.out.println(Thread.currentThread().getName() + "停车完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, i+"号车").start();
        }
    }
}
