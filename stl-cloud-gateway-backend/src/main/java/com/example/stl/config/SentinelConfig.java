package com.example.stl.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.datasource.redis.config.RedisConnectionConfig;
import com.example.stl.config.sentinel.CustomBlockExceptionHandler;
import com.example.stl.config.sentinel.Register2PropertyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * Sentinel 配置
 *
 * @author Aaric, created on 2021-09-14T15:48.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
@Configuration
public class SentinelConfig implements ApplicationRunner {

    /**
     * Redis Connection
     */
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.database}")
    private Integer redisDatabase;

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public SentinelConfig(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                          ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
//        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
//    }
    public CustomBlockExceptionHandler customBlockExceptionHandler() {
        return new CustomBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Redis
        RedisConnectionConfig redisConnectionConfig = RedisConnectionConfig.builder()
                .withHost(redisHost)
                .withPort(redisPort)
                .withPassword(redisPassword)
                .withDatabase(redisDatabase)
                .build();

        // FlowRule
        Register2PropertyUtil.register2FlowRules(redisConnectionConfig);

        // DegradeRule
        Register2PropertyUtil.register2DegradeRules(redisConnectionConfig);

        // ParamFlowRule
        Register2PropertyUtil.register2ParamFlowRules(redisConnectionConfig);

        // SystemRule
        Register2PropertyUtil.register2SystemRules(redisConnectionConfig);

        // AuthorityRule
        Register2PropertyUtil.register2AuthorityRules(redisConnectionConfig);

        // GatewayFlowRule
        Register2PropertyUtil.register2GatewayFlowRules(redisConnectionConfig);

        // log something
        log.info("sentinel rules loaded...");
    }
}
