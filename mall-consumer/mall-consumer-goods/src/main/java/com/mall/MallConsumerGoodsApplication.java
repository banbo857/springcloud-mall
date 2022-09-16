package com.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@SpringBootApplication
@SpringCloudApplication
@EnableEurekaClient
@MapperScan("com.mall.dao")
public class MallConsumerGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallConsumerGoodsApplication.class, args);
    }

}
