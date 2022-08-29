package com.example.bingfa;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @auther yangjianwu
 * @since 2022/7/8
 */
@Slf4j
public class ExecutorTest {
    private int corePoolSize = 2;
    private int maxPoolSize = 10;
    private int keepAliveSecond = 10;
    private int queueCapacity = 5;

    private static ThreadPoolTaskExecutor executor;

    @Before
    public void before() {
        executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("my-");
        executor.setCorePoolSize(this.corePoolSize);
        executor.setMaxPoolSize(this.maxPoolSize);
        executor.setKeepAliveSeconds(this.keepAliveSecond);
        executor.setQueueCapacity(this.queueCapacity);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        log.info("TaskExecutorConfig#threadExecutor 线程池初始化成功！");
    }

    @Test
    public void test() {
        int reqSize = 100;

        for (int i = 0; i < reqSize; i++) {
            //处理请求
            int finalI = i;
            executor.execute(() -> {

                process(finalI);

            });
            log.info("reqNo {} 分发成功", finalI);
        }
        log.info("main 执行完毕");
        while (true) {
        }
    }

    private void process(int reqNo) {
        int time = new Random().nextInt(6) + 1;
        log.info("线程 {} reqNo:{} 执行{}s", Thread.currentThread().getName(), reqNo, time);
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("线程 {} reqNo:{} 执行结束", Thread.currentThread().getName(), reqNo);
    }
}
