package com.mall.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义全局过滤器，需要实现GlobalFilter和Ordered接口
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    // 完成鉴权逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

//        获得请求和响应对象
        ServerHttpRequest request = exchange.getRequest().mutate().header("From", "gateway").build();
        ServerHttpResponse response = exchange.getResponse();

        log.info("url==>" + request.getURI());

        //白名单
        List<String> whiteList = new ArrayList<>();
        whiteList.add("/consumer/login/");
        whiteList.add("/seller/login/");
        for(String str : whiteList){
            if(request.getURI().getPath().contains(str)){
                log.info("白名单，放行{}",request.getURI().getPath());
                return chain.filter(exchange);
            }
        }

        //获取SessionId
        String sessionId = "";
        for(String cookie : request.getCookies().keySet()){
            if(cookie.equals("JSESSIONID")){
                sessionId = request.getCookies().getFirst(cookie).getValue();
//                log.info("sessionId==>" + sessionId);
                if (StringUtils.isEmpty(sessionId)) {
                    log.info("null session");
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }else {
                    // 继续执行filter链
                    return chain.filter(exchange.mutate().request(request.mutate().build()).build());
                }
            }
        }
        log.info("deny! no session");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    // 顺序，数值越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
