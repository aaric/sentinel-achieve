package com.example.stl.api.test.impl;

import com.example.stl.api.test.FeignApi;
import com.example.stl.api.test.feign.AbcApiFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试OpenFeign模块API接口控制器
 *
 * @author Aaric, created on 2021-11-19T17:38.
 * @version 5.2.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/test/feign")
public class FeignController implements FeignApi {

    @Autowired
    private AbcApiFeign abcApiFeign;

    @Override
    @GetMapping("/get")
    public String get(Long id) {
        String name = abcApiFeign.httpGet(id);
        log.info("feign -> {}: {}", id, name);
        return String.format("feign -> %s", name);
    }
}
