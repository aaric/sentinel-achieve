//package com.example.stl.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.netflix.zuul.filters.Route;
//import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Knife4j Swagger 资源配置
// *
// * @author Aaric, created on 2021-08-13T17:40.
// * @version 0.3.0-SNAPSHOT
// */
//@Slf4j
//@Primary
//@Component
//public class Knife4jResourcesConfig implements SwaggerResourcesProvider {
//
//    private final RouteLocator routeLocator;
//
//    public Knife4jResourcesConfig(RouteLocator routeLocator) {
//        this.routeLocator = routeLocator;
//    }
//
//    @Override
//    public List<SwaggerResource> get() {
//        List<SwaggerResource> resources = new ArrayList<>();
//        List<Route> routes = routeLocator.getRoutes();
//        routes.forEach(route -> {
//            // 限定gateway为"/api"前缀的路由规则
//            if (route.getFullPath().startsWith("/api")) {
//                log.info("{} -> {}", route.getId(), route.getFullPath());
//                resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "1.0"));
//            }
//        });
//        return resources;
//    }
//
//    /**
//     * 构建API资源对象
//     *
//     * @param name     API名称，即应用名称
//     * @param location API地址
//     * @param version  Swagger版本号
//     * @return
//     */
//    private SwaggerResource swaggerResource(String name, String location, String version) {
//        SwaggerResource resource = new SwaggerResource();
//        resource.setName(name);
//        resource.setLocation(location);
//        resource.setSwaggerVersion(version);
//        return resource;
//    }
//}
