package com.example.stl.api.test;

import com.example.stl.pojo.IdName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 测试abc模块API接口
 *
 * @author Aaric, created on 2021-09-14T14:03.
 * @version 0.5.1-SNAPSHOT
 */
@Api(tags = "测试abc模块API")
public interface AbcApi {

    @ApiOperation("httpGet")
    @GetMapping("/v1/test/abc/httpGet")
    String httpGet(@ApiParam(name = "id", example = "1") @RequestParam Long id);

    @ApiOperation("httpPost - not used")
    @PostMapping("/v1/test/abc/httpPost")
    String httpPost(@ApiParam(name = "id", example = "1") @RequestParam Long id,
                    @ApiParam(name = "name", example = "monkey") @RequestParam String name);

    @ApiOperation("httpPostJson")
    @PostMapping("/v1/test/abc/httpPostJson")
    String httpPostJson(@RequestBody IdName idName);

    @ApiOperation("httpPut - not used")
    @PutMapping("/v1/test/abc/httpPut")
    String httpPut(@ApiParam(name = "id", example = "1") @RequestParam Long id,
                   @ApiParam(name = "name", example = "monkey") @RequestParam String name);

    @ApiOperation("httpDelete")
    @DeleteMapping("/v1/test/abc/httpDelete")
    String httpDelete(@ApiParam(name = "id", example = "1") @RequestParam Long id);
}
