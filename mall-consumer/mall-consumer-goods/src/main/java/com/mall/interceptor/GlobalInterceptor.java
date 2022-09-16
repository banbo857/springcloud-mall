package com.mall.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

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
//        //自定义身份验证
//        String secretKey = request.getHeader("from");
//        log.info("url==>" + request.getRequestURI());
//        if(StringUtils.isBlank(secretKey) || !secretKey.equals("gateway")){
//            response.setContentType("application/json; charset=utf-8");
//            PrintWriter writer = response.getWriter();
//            writer.write("error");
//            return false;
//        }
        return true;
    }
}
