package com.example.stl.api.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 测试OpenFeign模块API接口
 *
 * @author Aaric, created on 2021-11-19T17:36.
 * @version 5.2.0-SNAPSHOT
 */
@Api(tags = "测试OpenFeign模块API")
public interface FeignApi {

    @ApiOperation("get")
    String get(@ApiParam(value = "ID", example = "1") Long id);
}
