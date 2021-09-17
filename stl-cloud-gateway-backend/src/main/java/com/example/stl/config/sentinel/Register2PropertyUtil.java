package com.example.stl.config.sentinel;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.redis.RedisDataSource;
import com.alibaba.csp.sentinel.datasource.redis.config.RedisConnectionConfig;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Set;

/**
 * register2Property 工具方法
 *
 * @author Aaric, created on 2021-09-17T14:10.
 * @version 0.3.0-SNAPSHOT
 */
public class Register2PropertyUtil {

    /**
     * FlowRule
     */
    public static final String SENTINEL_FLOW_RULE_KEY = "sentinel_flow_rules";
    public static final String SENTINEL_FLOW_RULE_CHANNEL = "sentinel_flow_rule_channel";

    public static void register2FlowRules(RedisConnectionConfig redisConnectionConfig) {
        Converter<String, List<FlowRule>> jsonFlowRuleParser = source -> JSON.parseObject(source,
                new TypeReference<List<FlowRule>>() {
                });
        ReadableDataSource<String, List<FlowRule>> redisFlowRuleDataSource = new RedisDataSource<>(redisConnectionConfig,
                SENTINEL_FLOW_RULE_KEY,
                SENTINEL_FLOW_RULE_CHANNEL,
                jsonFlowRuleParser);
        FlowRuleManager.register2Property(redisFlowRuleDataSource.getProperty());
    }

    /**
     * DegradeRule
     */
    public static final String SENTINEL_DEGRADE_RULE_KEY = "sentinel_degrade_rules";
    public static final String SENTINEL_DEGRADE_RULE_CHANNEL = "sentinel_degrade_rule_channel";

    public static void register2DegradeRules(RedisConnectionConfig redisConnectionConfig) {
        Converter<String, List<DegradeRule>> jsonDegradeRuleParser = source -> JSON.parseObject(source,
                new TypeReference<List<DegradeRule>>() {
                });
        ReadableDataSource<String, List<DegradeRule>> redisDegradeRuleDataSource = new RedisDataSource<>(redisConnectionConfig,
                SENTINEL_DEGRADE_RULE_KEY,
                SENTINEL_DEGRADE_RULE_CHANNEL,
                jsonDegradeRuleParser);
        DegradeRuleManager.register2Property(redisDegradeRuleDataSource.getProperty());
    }

    /**
     * ParamFlowRule
     */
    public static final String SENTINEL_PARAM_FLOW_RULE_KEY = "sentinel_param_flow_rules";
    public static final String SENTINEL_PARAM_FLOW_RULE_CHANNEL = "sentinel_param_flow_rule_channel";

    public static void register2ParamFlowRules(RedisConnectionConfig redisConnectionConfig) {
        Converter<String, List<ParamFlowRule>> jsonParamFlowRuleParser = source -> JSON.parseObject(source,
                new TypeReference<List<ParamFlowRule>>() {
                });
        ReadableDataSource<String, List<ParamFlowRule>> redisParamFlowRuleDataSource = new RedisDataSource<>(redisConnectionConfig,
                SENTINEL_PARAM_FLOW_RULE_KEY,
                SENTINEL_PARAM_FLOW_RULE_CHANNEL,
                jsonParamFlowRuleParser);
        ParamFlowRuleManager.register2Property(redisParamFlowRuleDataSource.getProperty());
    }

    /**
     * SystemRule
     */
    public static final String SENTINEL_SYSTEM_RULE_KEY = "sentinel_system_rules";
    public static final String SENTINEL_SYSTEM_RULE_CHANNEL = "sentinel_system_rule_channel";

    public static void register2SystemRules(RedisConnectionConfig redisConnectionConfig) {
        Converter<String, List<SystemRule>> jsonSystemRuleParser = source -> JSON.parseObject(source,
                new TypeReference<List<SystemRule>>() {
                });
        ReadableDataSource<String, List<SystemRule>> redisSystemRuleDataSource = new RedisDataSource<>(redisConnectionConfig,
                SENTINEL_SYSTEM_RULE_KEY,
                SENTINEL_SYSTEM_RULE_CHANNEL,
                jsonSystemRuleParser);
        SystemRuleManager.register2Property(redisSystemRuleDataSource.getProperty());
    }

    /**
     * AuthorityRule
     */
    public static final String SENTINEL_AUTHORITY_RULE_KEY = "sentinel_authority_rules";
    public static final String SENTINEL_AUTHORITY_RULE_CHANNEL = "sentinel_authority_rule_channel";

    public static void register2AuthorityRules(RedisConnectionConfig redisConnectionConfig) {
        Converter<String, List<AuthorityRule>> jsonAuthorityRuleParser = source -> JSON.parseObject(source,
                new TypeReference<List<AuthorityRule>>() {
                });
        ReadableDataSource<String, List<AuthorityRule>> redisAuthorityRuleDataSource = new RedisDataSource<>(redisConnectionConfig,
                SENTINEL_AUTHORITY_RULE_KEY,
                SENTINEL_AUTHORITY_RULE_CHANNEL,
                jsonAuthorityRuleParser);
        AuthorityRuleManager.register2Property(redisAuthorityRuleDataSource.getProperty());
    }

    /**
     * GatewayFlowRule
     */
    public static final String SENTINEL_GATEWAY_FLOW_RULE_KEY = "sentinel_gateway_flow_rules";
    public static final String SENTINEL_GATEWAY_FLOW_RULE_CHANNEL = "sentinel_gateway_flow_rule_channel";

    public static void register2GatewayFlowRules(RedisConnectionConfig redisConnectionConfig) {
        Converter<String, Set<GatewayFlowRule>> jsonGatewayFlowRuleParser = source -> JSON.parseObject(source,
                new TypeReference<Set<GatewayFlowRule>>() {
                });
        ReadableDataSource<String, Set<GatewayFlowRule>> redisGatewayFlowRuleDataSource = new RedisDataSource<>(redisConnectionConfig,
                SENTINEL_GATEWAY_FLOW_RULE_KEY,
                SENTINEL_GATEWAY_FLOW_RULE_CHANNEL,
                jsonGatewayFlowRuleParser);
        GatewayRuleManager.register2Property(redisGatewayFlowRuleDataSource.getProperty());
    }
}
