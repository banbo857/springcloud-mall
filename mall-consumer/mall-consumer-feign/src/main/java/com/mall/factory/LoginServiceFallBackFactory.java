package com.mall.factory;

import com.mall.service.FeignLoginService;
import com.mall.utils.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginServiceFallBackFactory implements FallbackFactory<FeignLoginService> {

    @Override
    public FeignLoginService create(Throwable throwable) {
        log.error("异常原因:{}", throwable.getMessage(), throwable);
        return new FeignLoginService(){
            @Override
            public Result login(String account, String password) {
                //出现异常，自定义返回内容，保证接口安全
                return Result.error("服务器异常，请稍后再试");
            }

            @Override
            public Result register(String account, String password) {
                //出现异常，自定义返回内容，保证接口安全
                return Result.error("服务器异常，请稍后再试");
            }
        };
    }
}
