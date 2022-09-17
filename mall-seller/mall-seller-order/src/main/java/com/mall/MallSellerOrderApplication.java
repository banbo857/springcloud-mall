package com.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableEurekaClient
@MapperScan("com.mall.dao")
@EnableFeignClients(basePackages = "com.mall")
@EnableHystrixDashboard
public class MallSellerOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSellerOrderApplication.class, args);
    }

}
