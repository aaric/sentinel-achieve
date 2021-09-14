package com.example.stl.api.test.impl;

import com.alibaba.csp.sentinel.*;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.example.stl.api.test.DefineApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 定义资源模块API接口控制器
 *
 * @author Aaric, created on 2021-09-13T14:55.
 * @version 0.2.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/test/define")
public class DefineController implements DefineApi {

    @PostConstruct
    public void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule qpsRule = new FlowRule()
                .setGrade(RuleConstant.FLOW_GRADE_QPS)
                // limit qps to 2
                .setCount(2);
        qpsRule.setResource("except");
        rules.add(qpsRule);
        FlowRuleManager.loadRules(rules);
    }

    @Override
    @GetMapping("/except")
    public String except() {
        try (Entry entry = SphU.entry("except")) {
            return "sentinel except";

        } catch (BlockException e) {
            log.error("except exception", e);
            return "sentinel except error";
        }
    }

    @Override
    @GetMapping("/bool")
    public String bool() {
        if (SphO.entry("bool")) {
            try {
                return "sentinel bool";

            } finally {
                SphO.exit();
            }
        } else {
            return "sentinel bool error";
        }
    }

    @Override
    @GetMapping("/annotate")
    @SentinelResource(value = "annotate", entryType = EntryType.IN, blockHandler = "annotateBlockHandler")
    public String annotate() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            log.error("annotate exception", e);
        }
        return "sentinel annotate";
    }

    public String annotateBlockHandler(BlockException e) {
        return "sentinel annotate block error";
    }

    @Override
    @GetMapping("/async")
    public void async() {
        try (AsyncEntry entry = SphU.asyncEntry("async")) {

            doAsync();

        } catch (BlockException e) {
            log.error("async exception", e);
        }
    }

    @Async
    public void doAsync() {
        try {
            log.info("async begin");
            TimeUnit.SECONDS.sleep(5);
            log.info("async end");

        } catch (InterruptedException e) {
            log.error("doAsync exception", e);
        }
    }
}
