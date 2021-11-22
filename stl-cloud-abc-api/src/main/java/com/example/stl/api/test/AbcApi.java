package com.example.stl.api.test;

import com.example.stl.pojo.IdName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 测试abc模块API接口
 *
 * @author Aaric, created on 2021-09-14T14:03.
 * @version 0.5.1-SNAPSHOT
 */
@Api(tags = "测试abc模块API")
public interface AbcApi {

    @ApiOperation("httpGet")
    @GetMapping("/api/abc/v1/test/abc/httpGet")
    String httpGet(@ApiParam(value = "ID", example = "1") @RequestParam Long id);

    @ApiOperation("httpPost")
    @PostMapping("/api/abc/v1/test/abc/httpPost")
    String httpPost(@ApiParam(value = "ID", example = "1") @RequestParam Long id,
                    @ApiParam(value = "Name", example = "monkey") @RequestParam String name);

    @ApiOperation("httpPostJson")
    @PostMapping("/api/abc/v1/test/abc/httpPostJson")
    String httpPostJson(@RequestBody IdName idName);

    @ApiOperation("httpPostFile")
    @PostMapping("/api/abc/v1/test/abc/httpPostFile")
    String httpPostFile(@ApiParam(value = "File(size<200MB)", required = true) @RequestPart MultipartFile uploadFile);

    @ApiOperation("httpPut")
    @PutMapping("/api/abc/v1/test/abc/httpPut")
    String httpPut(@ApiParam(value = "ID", example = "1") @RequestParam Long id,
                   @ApiParam(value = "Name", example = "monkey") @RequestParam String name);

    @ApiOperation("httpDelete")
    @DeleteMapping("/api/abc/v1/test/abc/httpDelete")
    String httpDelete(@ApiParam(value = "ID", example = "1") @RequestParam Long id);
}
