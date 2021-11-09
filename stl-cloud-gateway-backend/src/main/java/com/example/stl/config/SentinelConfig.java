//package com.example.stl.config;
//
//import com.alibaba.csp.sentinel.datasource.redis.config.RedisConnectionConfig;
//import com.example.stl.config.sentinel.Register2PropertyUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Sentinel 配置
// *
// * @author Aaric, created on 2021-09-14T15:48.
// * @version 0.3.0-SNAPSHOT
// */
//@Slf4j
//@Configuration
//public class SentinelConfig implements ApplicationRunner {
//
//    /**
//     * Redis Connection
//     */
//    @Value("${spring.redis.host}")
//    private String redisHost;
//
//    @Value("${spring.redis.port}")
//    private Integer redisPort;
//
//    @Value("${spring.redis.password}")
//    private String redisPassword;
//
//    @Value("${spring.redis.database}")
//    private Integer redisDatabase;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        // Redis
//        RedisConnectionConfig redisConnectionConfig = RedisConnectionConfig.builder()
//                .withHost(redisHost)
//                .withPort(redisPort)
//                .withPassword(redisPassword)
//                .withDatabase(redisDatabase)
//                .build();
//
//        // FlowRule
//        Register2PropertyUtil.register2FlowRules(redisConnectionConfig);
//
//        // DegradeRule
//        Register2PropertyUtil.register2DegradeRules(redisConnectionConfig);
//
//        // ParamFlowRule
//        Register2PropertyUtil.register2ParamFlowRules(redisConnectionConfig);
//
//        // SystemRule
//        Register2PropertyUtil.register2SystemRules(redisConnectionConfig);
//
//        // AuthorityRule
//        Register2PropertyUtil.register2AuthorityRules(redisConnectionConfig);
//
//        // GatewayFlowRule
//        Register2PropertyUtil.register2GatewayFlowRules(redisConnectionConfig);
//
//        // log something
//        log.info("sentinel rules loaded...");
//    }
//}
