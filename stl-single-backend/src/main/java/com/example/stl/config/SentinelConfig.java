package com.example.stl.config;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.redis.RedisDataSource;
import com.alibaba.csp.sentinel.datasource.redis.config.RedisConnectionConfig;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

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
     * FlowRule
     */
    public static final String SENTINEL_FLOW_RULE_KEY = "sentinel_flow_rules";
    public static final String SENTINEL_FLOW_RULE_CHANNEL = "sentinel_flow_rule_channel";

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
        Converter<String, List<FlowRule>> jsonFlowRuleParser = source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
        });
        ReadableDataSource<String, List<FlowRule>> redisFlowRuleDataSource = new RedisDataSource<>(redisConnectionConfig,
                SENTINEL_FLOW_RULE_KEY,
                SENTINEL_FLOW_RULE_CHANNEL,
                jsonFlowRuleParser);
        FlowRuleManager.register2Property(redisFlowRuleDataSource.getProperty());

        // log something
        log.info("sentinel rules loaded...");
    }
}
