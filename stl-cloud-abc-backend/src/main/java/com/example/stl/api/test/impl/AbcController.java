package com.example.stl.api.test.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.stl.api.test.AbcApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试abc模块API接口控制器
 *
 * @author Aaric, created on 2021-09-14T14:08.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/v1/test/abc")
public class AbcController implements AbcApi {

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
    }

    @Override
    @GetMapping("/echo")
    @SentinelResource("abc-echo")
    public String echo() {
        HttpServletRequest request = getRequest();
        log.info("{}, abc -> echo", request.getHeader("Authorization"));
        return "echo";
    }

    @Override
    @GetMapping("/httpGet")
    public String httpGet(@RequestParam String key) {
        log.info("httpGet -> key: {}", key);
        return String.format("httpGet: %s", key);
    }

    @Override
    @PostMapping("/httpPost")
    public String httpPost(@RequestParam String key, @RequestParam String value) {
        log.info("httpPost -> key: {}, value: {}", key, value);
        return String.format("httpPost: %s-%s", key, value);
    }

    @Override
    @PutMapping("/httpPut")
    public String httpPut(@RequestParam String key, @RequestParam String value) {
        log.info("httpPut -> key: {}, value: {}", key, value);
        return String.format("httpPut: %s-%s", key, value);
    }

    @Override
    @DeleteMapping("/httpDelete")
    public String httpDelete(@RequestParam String key) {
        log.info("httpDelete -> key: {}", key);
        return String.format("httpDelete: %s", key);
    }
}
