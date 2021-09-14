package com.example.stl.api.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 定义资源模块API接口
 *
 * @author Aaric, created on 2021-09-13T14:53.
 * @version 0.2.0-SNAPSHOT
 */
@Api(tags = "定义资源模块API")
public interface DefineApi {

    @ApiOperation("except")
    String except();

    @ApiOperation("bool")
    String bool();

    @ApiOperation("async")
    void async();

    @ApiOperation("annotate")
    String annotate();

    @ApiOperation("param")
    String param(@ApiParam(value = "ID", example = "1") Integer id);
}
