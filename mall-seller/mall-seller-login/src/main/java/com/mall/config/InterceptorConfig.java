package com.mall.config;

import com.mall.interceptor.GlobalInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * 拦截器配置
 */
@Component
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Resource
    private GlobalInterceptor globalInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor)
                .addPathPatterns("/seller/login/**") //拦截所有请求
                .excludePathPatterns(" /static/**");//静态资源过滤
        super.addInterceptors(registry);
    }
}
