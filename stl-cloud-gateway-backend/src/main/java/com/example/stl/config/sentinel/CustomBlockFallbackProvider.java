package com.example.stl.config.sentinel;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.BlockResponse;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackProvider;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义 FallbackProvider
 *
 * @author Aaric, created on 2021-09-16T17:37.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
public class CustomBlockFallbackProvider implements ZuulBlockFallbackProvider {

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public BlockResponse fallbackResponse(String route, Throwable cause) {
        BlockResponse response = null;

        if (cause instanceof FlowException) {
            log.error("fallback: sentinel flow exception, route={}", route);
            response = new BlockResponse(501, "sentinel flow exception", route);

        } else if (cause instanceof ParamFlowException) {
            log.error("fallback: sentinel param flow exception, route={}", route);
            response = new BlockResponse(501, "sentinel param flow exception", route);

        } else if (cause instanceof DegradeException) {
            log.error("fallback: sentinel degrade exception, route={}", route);
            response = new BlockResponse(501, "sentinel degrade exception", route);

        } else if (cause instanceof AuthorityException) {
            log.error("fallback: sentinel authority exception, route={}", route);
            response = new BlockResponse(501, "sentinel authority exception", route);

        } else if (cause instanceof SystemBlockException) {
            log.error("fallback: sentinel system block exception, route={}", route);
            response = new BlockResponse(501, "sentinel system block exception", route);

        } else if (cause instanceof BlockException) {
            log.error("fallback: sentinel block exception, route={}", route);
            response = new BlockResponse(501, "sentinel block exception", route);

        } else {
            log.error("fallback: sentinel error, route={}", route);
            response = new BlockResponse(503, "sentinel error", route);
        }

        // return
        return response;
    }
}
