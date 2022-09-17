package com.mall.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class FeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //添加请求头
        requestTemplate.header("Call", "feign");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            String from = request.getHeader("From");
            if(StringUtils.isBlank(from) && !from.equals("gateway")){
                HttpServletResponse response = requestAttributes.getResponse();
                response.setContentType("application/json; charset=utf-8");
                PrintWriter writer = null;
                try {
                    writer = response.getWriter();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                writer.write("deny! invalid request");
            }
            //添加cookie
            String cookie = request.getHeader("Cookie");
            requestTemplate.header("Cookie", cookie);
            requestTemplate.header("From",from);
        }
    }
}
