//package com.example.stl.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//import springfox.documentation.swagger.web.*;
//
//import java.util.Optional;
//
///**
// * 自定义Knife4j Swagger端点配置（不配置也可以）
// *
// * @author Aaric, created on 2021-11-10T16:13.
// * @version 0.5.0-SNAPSHOT
// */
//@RestController
//public class Knife4HandlerConfig {
//
//    @Autowired(required = false)
//    private SecurityConfiguration securityConfiguration;
//
//    @Autowired(required = false)
//    private UiConfiguration uiConfiguration;
//
//    private final SwaggerResourcesProvider swaggerResources;
//
//    @Autowired
//    public Knife4HandlerConfig(SwaggerResourcesProvider swaggerResources) {
//        this.swaggerResources = swaggerResources;
//    }
//
//    /**
//     * Swagger安全配置，支持oauth和apiKey设置
//     *
//     * @return
//     */
//    @GetMapping("/swagger-resources/configuration/security")
//    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
//        return Mono.just(new ResponseEntity<>(
//                Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()), HttpStatus.OK));
//    }
//
//    /**
//     * Swagger UI配置
//     *
//     * @return
//     */
//    @GetMapping("/swagger-resources/configuration/ui")
//    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
//        return Mono.just(new ResponseEntity<>(
//                Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()), HttpStatus.OK));
//    }
//
//    /**
//     * Swagger UI配置
//     *
//     * @return
//     */
//    @GetMapping("/swagger-resources")
//    public Mono<ResponseEntity> swaggerResources() {
//        return Mono.just((new ResponseEntity<>(swaggerResources.get(), HttpStatus.OK)));
//    }
//}
