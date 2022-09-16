package com.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringCloudApplication
@EnableCircuitBreaker
@MapperScan("com.mall.dao")
public class MallConsumerPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallConsumerPayApplication.class, args);
    }

}
