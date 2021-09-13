package com.example.stl.api.test.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.stl.api.test.TestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 简单测试模块API接口控制器
 *
 * @author Aaric, created on 2021-09-13T14:55.
 * @version 0.1.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/test/test")
public class TestController implements TestApi {

    @Override
    @GetMapping("/hello")
    @SentinelResource("hello")
    public String hello() {
        return "hello world";
    }
}
