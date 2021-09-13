package com.example.stl.api.test.impl;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.example.stl.api.test.TestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单测试模块API接口控制器
 *
 * @author Aaric, created on 2021-09-13T14:55.
 * @version 0.1.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/test/test")
public class TestController implements TestApi {

    @PostConstruct
    public void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule qpsRule = new FlowRule()
                .setGrade(RuleConstant.FLOW_GRADE_QPS)
                // limit qps to 2
                .setCount(2);
        qpsRule.setResource("hello");
        rules.add(qpsRule);
        FlowRuleManager.loadRules(rules);
    }

    @Override
    @GetMapping("/hello")
    //@SentinelResource("hello")
    public String hello() {
        try (Entry entry = SphU.entry("hello")) {
            return "hello sentinel";

        } catch (BlockException e) {
            log.error("hello exception", e);
            return "hello error";
        }
    }
}
