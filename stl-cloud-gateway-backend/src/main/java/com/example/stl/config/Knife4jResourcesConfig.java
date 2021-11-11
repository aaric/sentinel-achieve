package com.example.stl.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Knife4j Swagger 资源配置
 *
 * @author Aaric, created on 2021-08-13T17:40.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
@Primary
@Component
public class Knife4jResourcesConfig implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

    public Knife4jResourcesConfig(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
        this.routeLocator = routeLocator;
        this.gatewayProperties = gatewayProperties;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();

        // 获取所有路由ID
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));

        // 过滤出配置文件中定义的路由
        Stream<RouteDefinition> routeDefinitionStream = gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()));

        // 过滤出Path Route Predicate路由规则
        routeDefinitionStream.forEach(route -> {
            route.getPredicates().stream()
                    .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
                    .forEach(predicateDefinition -> resources.add(swaggerResource(route.getId(),
                            predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("**", "v2/api-docs"),
                            "2.0")));
        });

        return resources;
    }

    /**
     * 构建API资源对象
     *
     * @param name     API名称，即应用名称
     * @param location API地址
     * @param version  Swagger版本号
     * @return
     */
    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource resource = new SwaggerResource();
        resource.setName(name);
        resource.setLocation(location);
        resource.setSwaggerVersion(version);
        return resource;
    }
}
