package com.example.stl.api.test.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.stl.api.test.AbcApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @Override
    @GetMapping("/echo")
    @SentinelResource("abc-echo")
    public String echo() {
        HttpServletRequest request = getRequest();
        log.info("{}, abc -> echo", request.getHeader("Authorization"));
        return "echo";
    }

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
    }
}
