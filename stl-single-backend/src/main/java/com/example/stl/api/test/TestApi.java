package com.example.stl.api.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 简单测试模块API接口
 *
 * @author Aaric, created on 2021-09-13T14:53.
 * @version 0.1.0-SNAPSHOT
 */
@Api(tags = "简单测试模块API")
public interface TestApi {

    @ApiOperation("hello")
    String hello();
}
