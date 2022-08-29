package com.example.java;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.Pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

/**
 * @auther yangjianwu
 * @since 2022/7/18
 */
@Data
@Slf4j
public class TestStreamApi {

    @Test
    public void test1(){
        System.out.println(LocalDateTime.now().minusDays(1));

        System.out.println(Date.from(LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant()));
    }

    @Test
    public void test() {
        Long size = 20l;
        size = size == null ? 0 : size > 200 ? 200 : size;
        System.out.println(size);

        String str = "{\"id\":1,\"name\":\"yang\"}";
        System.out.println(str);
        System.out.println(JSON.parse(JSON.parse(JSON.parse(str).toString()).toString()));
        String yang = JSONObject.toJSONString(new Pojo(1, "yang"));
        System.out.println(yang);
        System.out.println(JSONObject.toJSONString(yang));

        System.out.println(JSONObject.parse(JSONObject.toJSONString(yang)));
        System.out.println(JSONObject.parse(JSONObject.toJSONString(JSONObject.toJSONString(yang))));
    }

    @Test
    public void testJson() {
        FxMessage<String> yang = new FxMessage<>("111", JSONObject.toJSONString(new Pojo(1, "yang")));
        log.info("==> {}",yang);
        System.out.println(getStrBody(yang));
    }

    @Test
    public void testJson1() {
        FxMessage<String> yang = new FxMessage<>("111", "{\"id\":1,\"name\":\"yang\"}");
        log.info("==> {}",yang);

        System.out.println(getStrBody(yang));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FxMessage<T> implements Serializable {
        private String msgId;
        private T content;
    }

    private static <T> String getStrBody(T body) {
        return Optional.of(body)
                .filter(b -> b instanceof String)
                .map(b -> (String) b)
                .orElse(JSON.toJSONString(body));
    }
}
