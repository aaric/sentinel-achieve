package com.example.stl.api.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 测试client模块API接口
 *
 * @author Aaric, created on 2021-09-14T14:05.
 * @version 0.5.1-SNAPSHOT
 */
@Api(tags = "测试client模块API")
public interface ClientApi {

    @ApiOperation("get")
    String get(@ApiParam(value = "ID", example = "1") Long id);
}
