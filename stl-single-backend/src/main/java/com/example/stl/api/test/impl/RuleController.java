package com.example.stl.api.test.impl;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.example.stl.api.test.RuleApi;
import com.example.stl.api.test.feign.AbcApiFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

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

    @Autowired
    private AbcApiFeign abcApiFeign;

    @Override
    @GetMapping("/flow")
    public boolean flow(@RequestParam String resName) {
        System.err.println(abcApiFeign.echo());
//        try {
//            FlowRule flowRule = new FlowRule()
//                    // 限流策略：QPS
//                    .setGrade(RuleConstant.FLOW_GRADE_QPS)
//                    // 单机阈值
//                    .setCount(2);
//            flowRule.setResource(resName);
//            FlowRuleManager.loadRules(Collections.singletonList(flowRule));
//
//            return true;
//
//        } catch (Exception e) {
//            log.error("flow exception", e);
//        }
        return false;
    }

    @Override
    @GetMapping("/degrade")
    public boolean degrade(@RequestParam String resName) {
        try {
            DegradeRule degradeRule = new DegradeRule()
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
            degradeRule.setResource(resName);
            DegradeRuleManager.loadRules(Collections.singletonList(degradeRule));

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
            SystemRule systemRule = new SystemRule();
            // 阈值类型：入口 QPS
            systemRule.setQps(2);
            SystemRuleManager.loadRules(Collections.singletonList(systemRule));

            return true;

        } catch (Exception e) {
            log.error("system exception", e);
        }
        return false;
    }

    @Override
    @GetMapping("/authority")
    public boolean authority(@RequestParam String resName, @RequestParam String ipAddress) {
        try {
            AuthorityRule authorityRule = new AuthorityRule()
                    // 授权类型：黑名单
                    .setStrategy(RuleConstant.AUTHORITY_BLACK);
            authorityRule.setLimitApp(ipAddress);
            authorityRule.setResource(resName);
            AuthorityRuleManager.loadRules(Collections.singletonList(authorityRule));

            return true;

        } catch (Exception e) {
            log.error("system exception", e);
        }
        return false;
    }

    @Override
    @GetMapping("/param")
    public boolean param(@RequestParam String resName, @RequestParam Integer paramIndex, @RequestParam String paramValue) {
        try {
            ParamFlowRule paramFlowRule = new ParamFlowRule()
                    .setParamIdx(paramIndex)
                    .setCount(10);
            ParamFlowItem paramFlowItem = new ParamFlowItem()
                    .setClassType(Integer.class.getName())
                    .setObject(paramValue)
                    .setCount(2);
            paramFlowRule.setResource(resName);
            paramFlowRule.setParamFlowItemList(Collections.singletonList(paramFlowItem));
            ParamFlowRuleManager.loadRules(Collections.singletonList(paramFlowRule));

            return true;

        } catch (Exception e) {
            log.error("param exception", e);
        }
        return false;
    }
}
