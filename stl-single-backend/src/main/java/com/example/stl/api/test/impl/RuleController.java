package com.example.stl.api.test.impl;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.example.stl.api.test.RuleApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义规则模块API接口控制器
 *
 * @author Aaric, created on 2021-09-14T14:31.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/test/rule")
public class RuleController implements RuleApi {

    @Override
    @GetMapping("/flow")
    public boolean flow(@RequestParam String resName) {
        try {
            List<FlowRule> rules = new ArrayList<>();
            FlowRule flowRule = new FlowRule()
                    // 限流策略：QPS
                    .setGrade(RuleConstant.FLOW_GRADE_QPS)
                    // 单机阈值
                    .setCount(2);
            flowRule.setResource(resName);
            rules.add(flowRule);
            FlowRuleManager.loadRules(rules);

            return true;

        } catch (Exception e) {
            log.error("flow exception", e);
        }
        return false;
    }

    @Override
    @GetMapping("/degrade")
    public boolean degrade(@RequestParam String resName) {
        try {
            List<DegradeRule> rules = new ArrayList<>();
            DegradeRule rtRule = new DegradeRule()
                    // 熔断策略: 慢调用比例
                    .setGrade(RuleConstant.DEGRADE_GRADE_RT)
                    // 最大RT响应时间，单位ms
                    .setCount(1)
                    // 比例阈值
                    .setSlowRatioThreshold(1.0)
                    // 熔断时长
                    .setTimeWindow(10)
                    // 最小请求数
                    .setMinRequestAmount(1);
            rtRule.setResource(resName);
            rules.add(rtRule);
            DegradeRuleManager.loadRules(rules);

            return true;

        } catch (Exception e) {
            log.error("degrade exception", e);
        }
        return false;
    }

    @Override
    @GetMapping("/system")
    public boolean system() {
        try {
            List<SystemRule> rules = new ArrayList<>();
            SystemRule systemRule = new SystemRule();
            // 阈值类型：入口 QPS
            systemRule.setQps(2);
            rules.add(systemRule);
            SystemRuleManager.loadRules(rules);

            return true;

        } catch (Exception e) {
            log.error("system exception", e);
        }
        return false;
    }
}
