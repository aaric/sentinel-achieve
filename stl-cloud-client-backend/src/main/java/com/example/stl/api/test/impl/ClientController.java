package com.example.stl.api.test.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.stl.api.test.ClientApi;
import com.example.stl.api.test.feign.AbcApiFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试client模块API接口控制器
 *
 * @author Aaric, created on 2021-09-14T14:18.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/v1/test/client")
public class ClientController implements ClientApi {

    @Autowired
    private AbcApiFeign abcApiFeign;

    @Override
    @GetMapping("/httpEcho")
    @SentinelResource("client-http-echo")
    public String httpEcho() {
        String echo = abcApiFeign.echo();
        log.info("client -> httpEcho: {}", echo);
        return "httpEcho: " + echo;
    }
}
