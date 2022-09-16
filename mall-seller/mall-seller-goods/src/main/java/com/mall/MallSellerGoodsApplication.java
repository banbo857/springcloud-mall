package com.mall;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

//@SpringBootApplication
@SpringCloudApplication
@EnableEurekaClient
@MapperScan("com.mall.dao")
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class MallSellerGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSellerGoodsApplication.class, args);
    }

}
