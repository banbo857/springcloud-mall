package com.mall.service;

import com.mall.config.FeignConfig;
import com.mall.factory.LoginServiceFallBackFactory;
import com.mall.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Component
@FeignClient(value = "mall-seller-login", fallbackFactory = LoginServiceFallBackFactory.class, configuration = FeignConfig.class)
public interface SellerFeignLoginService {

    @PostMapping("/seller/login/login")
    Result login(@RequestParam("account") String account,
                 @RequestParam("password") String password);

    @PostMapping("/seller/login/register")
    Result register(@RequestParam("account") String account,
                    @RequestParam("password") String password,
                    @RequestParam("storeName") String storeName,
                    @RequestParam("storeIntroduce") String storeIntroduce);

}
