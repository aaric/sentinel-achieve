package com.example.stl.config.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 自定义 BlockExceptionHandler
 *
 * @author Aaric, created on 2021-09-16T17:37.
 * @version 0.5.0-SNAPSHOT
 */
@Slf4j
public class CustomBlockExceptionHandler implements WebExceptionHandler {

    private List<ViewResolver> viewResolvers;
    private List<HttpMessageWriter<?>> messageWriters;

    public CustomBlockExceptionHandler(List<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolvers;
        this.messageWriters = serverCodecConfigurer.getWriters();
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        String route = exchange.getRequest().getPath().value();
        Integer errCode = 501;
        String errMsg = "";

        if (ex instanceof FlowException) {
            log.error("fallback: sentinel flow exception, route={}", route);
            errMsg = String.format("%d: %s", errCode, "sentinel flow exception");

        } else if (ex instanceof ParamFlowException) {
            log.error("fallback: sentinel param flow exception, route={}", route);
            errMsg = String.format("%d: %s", errCode, "sentinel param flow exception");

        } else if (ex instanceof DegradeException) {
            log.error("fallback: sentinel degrade exception, route={}", route);
            errMsg = String.format("%d: %s", errCode, "sentinel degrade exception");

        } else if (ex instanceof AuthorityException) {
            log.error("fallback: sentinel authority exception, route={}", route);
            errMsg = String.format("%d: %s", errCode, "sentinel authority exception");

        } else if (ex instanceof SystemBlockException) {
            log.error("fallback: sentinel system block exception, route={}", route);
            errMsg = String.format("%d: %s", errCode, "sentinel system block exception");

        } else if (ex instanceof BlockException) {
            log.error("fallback: sentinel block exception, route={}", route);
            errMsg = String.format("%d: %s", errCode, "sentinel block exception");

        } else {
            log.error("fallback: default sentinel error, route={}", route);
            errMsg = String.format("%d: %s", errCode, "default sentinel error");
        }

        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer buffer = response.bufferFactory().wrap(errMsg.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
