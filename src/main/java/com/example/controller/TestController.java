package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @auther yangjianwu
 * @since 2022/7/20
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public String test(){
        String uuid = UUID.randomUUID().toString();
        log.info(uuid);

        new Thread(()->{
            log.info("aa");
        }).start();
        return uuid;
    }

    @GetMapping("/test1")
    public String test1(){
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        return uuid;
    }
}
