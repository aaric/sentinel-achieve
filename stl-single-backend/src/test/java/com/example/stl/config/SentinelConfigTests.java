package com.example.stl.config;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.example.stl.config.sentinel.Register2PropertyUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

/**
 * SentinelConfigTests
 *
 * @author Aaric, created on 2021-09-16T11:40.
 * @version 0.3.0-SNAPSHOT
 */
@Disabled
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SentinelConfigTests {

    @Autowired
    private RedisURI redisUri;

    private <T> void pushRules(List<T> rules, Converter<List<T>, String> encoder) {
        //RedisClient client = RedisClient.create("redis://redis@127.0.0.1:6379/0?timeout=15s");
        RedisClient client = RedisClient.create(redisUri);
        StatefulRedisPubSubConnection<String, String> connection = client.connectPubSub();
        RedisPubSubCommands<String, String> subCommands = connection.sync();
        String value = encoder.convert(rules);
        subCommands.multi();
        subCommands.set(Register2PropertyUtil.SENTINEL_FLOW_RULE_KEY, value);
        subCommands.publish(Register2PropertyUtil.SENTINEL_FLOW_RULE_CHANNEL, value);
        subCommands.exec();
    }

    @Test
    public void testAddFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule qpsRule = new FlowRule()
                .setGrade(RuleConstant.FLOW_GRADE_QPS)
                // limit qps to 2
                .setCount(2);
        qpsRule.setResource("annotate");
        rules.add(qpsRule);

        Converter<List<FlowRule>, String> encoder = object -> JSON.toJSONString(object);
        pushRules(rules, encoder);
    }
}
