package com.mall.service;

import com.mall.factory.PayServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "mall-consumer-pay",fallbackFactory = PayServiceFallBackFactory.class)
@Component
public interface FeignPayService {

    @RequestMapping("/pay")
    boolean pay();

}
