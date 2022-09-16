package com.mall.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 全局拦截器
 */
@Slf4j
@Component
public class GlobalInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        log.info("url==>" + request.getRequestURI());

        //自定义身份验证
        String from = request.getHeader("From");
        log.info("from==>" + from);
        if(StringUtils.isBlank(from) || !from.equals("gateway")){
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("deny! invalid request");
            return false;
        }

        Cookie[] cookies = request.getCookies();
        //获取SessionId
        String sessionId = "";
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    sessionId = cookie.getValue();
                    log.info("sessionId==>" + sessionId);
                    break;
                }
            }
        }
        return true;
    }
}
