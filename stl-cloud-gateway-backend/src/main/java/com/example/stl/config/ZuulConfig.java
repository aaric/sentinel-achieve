package com.example.stl.config;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulErrorFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPostFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPreFilter;
import com.example.stl.config.sentinel.CustomBlockFallbackProvider;
import com.netflix.zuul.ZuulFilter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Zuul 网关配置
 *
 * @author Aaric, created on 2021-09-16T17:09.
 * @version 0.3.0-SNAPSHOT
 */
@Configuration
public class ZuulConfig implements ApplicationRunner {

    @Bean
    public ZuulFilter sentinelZuulPreFilter() {
        return new SentinelZuulPreFilter();
    }

    @Bean
    public ZuulFilter sentinelZuulPostFilter() {
        return new SentinelZuulPostFilter();
    }

    @Bean
    public ZuulFilter sentinelZuulErrorFilter() {
        return new SentinelZuulErrorFilter();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册 FallbackProvider
        ZuulBlockFallbackManager.registerProvider(new CustomBlockFallbackProvider());
    }
}
