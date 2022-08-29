package com.example.java;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @auther yangjianwu
 * @since 2022/7/18
 */
@Slf4j
public class TestException {
    @Test
    public void testflow() {
        new Thread(() -> {
            try {
                doSign();
            } catch (Exception e) {
                log.error("外面", e);
                throw new RuntimeException("wai");
            } finally {
                log.info("finally");
            }
        }).start();
        log.info("over");
    }

    private void doSign() {
        try {
            throw new RuntimeException("aa");
        } catch (Exception e) {
            log.error("aaa {}", "hah", e);
            int i = 0;
            int a = i / 0;
        }

    }

    @Test
    public void testReturn() {
        try {
            System.out.println(returnStr());
        } catch (Exception e) {
            Integer i = null;
            log.error("e ", e);
            log.error("e {}", e.getMessage());
            log.error("e {}", i);
            System.out.println("a"+i);
        }


    }

    public String returnStr() {
        try {
            doExecption();
            return "a";
        } finally {
            log.info("finally");
        }

    }

    private void doExecption() {
        throw new NullPointerException();
    }
}
