package com.example.stl.config.sentinel;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.BlockResponse;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackProvider;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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
        log.error("fallbackResponse: {}", route);
        if (cause instanceof BlockException) {
            return new BlockResponse(501, "Sentinel block exception", route);
        } else {
            return new BlockResponse(500, "System Error", route);
        }
    }
}
