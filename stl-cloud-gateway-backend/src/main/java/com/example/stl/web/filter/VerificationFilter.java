package com.example.stl.web.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * VerificationFilter
 *
 * @author Aaric, created on 2021-11-15T17:22.
 * @version 0.5.1-SNAPSHOT
 */
@Component
public class VerificationFilter implements GatewayFilter, Ordered {

    public static String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        // 获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        StringBuilder sb = new StringBuilder();

        body.subscribe(buffer -> {
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            String bodyString = new String(bytes, StandardCharsets.UTF_8);
            sb.append(bodyString);
        });

        // 去掉空格，换行和制表符
        return StringUtils.replace(sb.toString(), "\\s*|\t|\r|\n", "");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String bodyContent = resolveBodyFromRequest(exchange.getRequest());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
