package com.example.stl.api.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 定义规则模块API接口
 *
 * @author Aaric, created on 2021-09-14T14:30.
 * @version 0.3.0-SNAPSHOT
 */
@Api(tags = "定义规则模块API")
public interface RuleApi {

    @ApiOperation("flow")
    boolean flow(@ApiParam(value = "资源名称", example = "annotate") String resName);

    @ApiOperation("degrade")
    boolean degrade(@ApiParam(value = "资源名称", example = "annotate") String resName);

    @ApiOperation("system")
    boolean system();

    @ApiOperation("authority")
    boolean authority(@ApiParam(value = "资源名称", example = "annotate") String resName,
                      @ApiParam(value = "限制IP地址", example = "127.0.0.1") String ipAddress);

    @ApiOperation("param")
    boolean param(@ApiParam(value = "资源名称", example = "param") String resName,
                  @ApiParam(value = "参数名称", example = "id") String paramName);
}
