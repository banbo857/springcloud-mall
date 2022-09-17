package com.mall.filter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


//限流配置
@Configuration
public class RedisRateLimiterConfig {

    /**
     * 接口限流
     * 获取请求地址的uri作为限流key
     *
     * @return
     */
    @Bean("pathResolver")
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }


    /**
     * ip限流
     *
     * @return
     */
    @Bean("ipResolver")
    @Primary
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}


