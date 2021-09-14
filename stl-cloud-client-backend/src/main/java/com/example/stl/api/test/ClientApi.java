package com.example.stl.api.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测试client模块API接口
 *
 * @author Aaric, created on 2021-09-14T14:05.
 * @version 0.3.0-SNAPSHOT
 */
@Api(tags = "测试client模块API")
public interface ClientApi {

    @ApiOperation("httpEcho")
    String httpEcho();
}
