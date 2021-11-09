//package com.example.stl.config;
//
//import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
//import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
//import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
//import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackManager;
//import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulErrorFilter;
//import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPostFilter;
//import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPreFilter;
//import com.alibaba.csp.sentinel.slots.block.RuleConstant;
//import com.example.stl.config.sentinel.CustomBlockFallbackProvider;
//import com.netflix.zuul.ZuulFilter;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Collections;
//
///**
// * Zuul 网关配置
// *
// * @author Aaric, created on 2021-09-16T17:09.
// * @version 0.3.0-SNAPSHOT
// */
//@Configuration
//public class ZuulConfig implements ApplicationRunner {
//
//    @Bean
//    public ZuulFilter sentinelZuulPreFilter() {
//        return new SentinelZuulPreFilter();
//    }
//
//    @Bean
//    public ZuulFilter sentinelZuulPostFilter() {
//        return new SentinelZuulPostFilter();
//    }
//
//    @Bean
//    public ZuulFilter sentinelZuulErrorFilter() {
//        return new SentinelZuulErrorFilter();
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        // 注册 FallbackProvider
//        ZuulBlockFallbackManager.registerProvider(new CustomBlockFallbackProvider());
//
//        // initGatewayFlowRules
//        GatewayFlowRule gatewayFlowRule = new GatewayFlowRule()
//                .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_ROUTE_ID)
//                .setGrade(RuleConstant.FLOW_GRADE_QPS)
//                .setCount(2);
//        gatewayFlowRule.setResource("stl-cloud-client-backend");
//        GatewayRuleManager.loadRules(Collections.singleton(gatewayFlowRule));
//    }
//}
