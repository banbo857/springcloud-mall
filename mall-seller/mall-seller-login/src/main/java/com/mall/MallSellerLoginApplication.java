package com.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.mall.dao")
@EnableCircuitBreaker
public class MallSellerLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSellerLoginApplication.class, args);
    }

}
