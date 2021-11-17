package com.example.stl.api.test.feign;

import com.example.stl.api.test.AbcApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 测试abc模块API调用客户端
 *
 * @author Aaric, created on 2021-09-14T14:09.
 * @version 0.3.0-SNAPSHOT
 */
@FeignClient(value = "stl-cloud-abc-backend")
//@FeignClient(value = "${feign.custom-routes.app.name}", url = "${feign.custom-routes.app.url}")
public interface AbcApiFeign extends AbcApi {
}
