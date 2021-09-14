package com.example.stl.api.test.impl;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
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
            FlowRule qpsRule = new FlowRule()
                    .setGrade(RuleConstant.FLOW_GRADE_QPS)
                    // limit qps to 2
                    .setCount(2);
            qpsRule.setResource(resName);
            rules.add(qpsRule);
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
                    .setGrade(RuleConstant.DEGRADE_GRADE_RT)
                    .setCount(1)
                    .setSlowRatioThreshold(1.0)
                    .setTimeWindow(10)
                    .setMinRequestAmount(1);
            rtRule.setResource(resName);
            rules.add(rtRule);
            DegradeRuleManager.loadRules(rules);

            return true;

        } catch (Exception e) {
            log.error("flow exception", e);
        }
        return false;
    }
}
