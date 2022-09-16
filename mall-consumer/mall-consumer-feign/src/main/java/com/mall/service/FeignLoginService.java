package com.mall.service;

import com.mall.config.FeignConfig;
import com.mall.factory.LoginServiceFallBackFactory;
import com.mall.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "mall-consumer-login", fallbackFactory = LoginServiceFallBackFactory.class, configuration = FeignConfig.class)
public interface FeignLoginService {

    @PostMapping("/consumer/login/login")
    Result login(@RequestParam("account") String account,
                 @RequestParam("password") String password);

    @PostMapping("/consumer/login/register")
    Result register(@RequestParam("account") String account,
                    @RequestParam("password") String password);

}
