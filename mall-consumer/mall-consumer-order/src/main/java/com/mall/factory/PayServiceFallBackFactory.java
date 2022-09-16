package com.mall.factory;

import com.mall.service.FeignPayService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PayServiceFallBackFactory implements FallbackFactory<FeignPayService> {
    @Override
    public FeignPayService create(Throwable throwable) {
        log.error("异常原因:{}", throwable.getMessage(), throwable);
        return new FeignPayService(){
            @Override
            public boolean pay() {
                //出现异常，自定义返回内容，保证接口安全
                log.error("支付失败,请稍后再试");
                return false;
            }
        };
    }
}
