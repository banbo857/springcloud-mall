package com.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringCloudApplication
@EnableEurekaClient
public class MallConsumerCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallConsumerCartApplication.class, args);
    }

}
