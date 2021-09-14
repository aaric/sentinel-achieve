package com.example.stl.api.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 定义限流模块API接口
 *
 * @author Aaric, created on 2021-09-13T14:53.
 * @version 0.2.0-SNAPSHOT
 */
@Api(tags = "定义限流模块API")
public interface DefineApi {

    @ApiOperation("except")
    String except();

    @ApiOperation("bool")
    String bool();

    @ApiOperation("annotate")
    String annotate();

    @ApiOperation("async")
    void async();
}
