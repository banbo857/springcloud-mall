package com.mall.factory;

import com.mall.service.SellerFeignLoginService;
import com.mall.utils.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class LoginServiceFallBackFactory implements FallbackFactory<SellerFeignLoginService> {

    @Override
    public SellerFeignLoginService create(Throwable throwable) {
        log.error("异常原因:{}", throwable.getMessage(), throwable);
        return new SellerFeignLoginService() {
            @Override
            public Result login(String account, String password) {
                return Result.error("登录服务异常");
            }

            @Override
            public Result register(String account, String password, String storeName, String storeIntroduce) {
                return Result.error("注册服务异常");
            }
        };
    }
}
