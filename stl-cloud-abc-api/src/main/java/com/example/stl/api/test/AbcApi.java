package com.example.stl.api.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试abc模块API接口
 *
 * @author Aaric, created on 2021-09-14T14:03.
 * @version 0.3.0-SNAPSHOT
 */
@Api(tags = "测试abc模块API")
public interface AbcApi {

    @ApiOperation("echo")
    @GetMapping("/v1/test/abc/echo")
    String echo();
}
