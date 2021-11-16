package com.example.stl.api.test.impl;

import com.example.stl.api.test.AbcApi;
import com.example.stl.pojo.IdName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试abc模块API接口控制器
 *
 * @author Aaric, created on 2021-09-14T14:08.
 * @version 0.5.1-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/v1/test/abc")
public class AbcController implements AbcApi {

    @Override
    @GetMapping("/httpGet")
    public String httpGet(@RequestParam Long id) {
        HttpServletRequest request = getRequest();
        log.info("httpGet -> {}: {}", HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
        log.info("httpGet -> id: {}", id);
        return String.format("httpGet -> %s", id);
    }

    @Override
    @PostMapping("/httpPost")
    public String httpPost(@RequestParam Long id, @RequestParam String name) {
        log.info("httpPost -> id: {}, value: {}", id, name);
        return String.format("httpPost -> %s-%s", id, name);
    }

    @Override
    @PostMapping("/httpPostJson")
    public String httpPostJson(@RequestBody IdName idName) {
        log.info("httpPostJson -> id: {}, value: {}", idName.getId(), idName.getName());
        return String.format("httpPost -> %s-%s", idName.getId(), idName.getName());
    }

    @Override
    @PutMapping("/httpPut")
    public String httpPut(@RequestParam Long id, @RequestParam String name) {
        log.info("httpPut -> id: {}, value: {}", id, name);
        return String.format("httpPut -> %s-%s", id, name);
    }

    @Override
    @DeleteMapping("/httpDelete")
    public String httpDelete(@RequestParam Long id) {
        log.info("httpDelete -> id: {}", id);
        return String.format("httpDelete -> %s", id);
    }

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
    }
}
