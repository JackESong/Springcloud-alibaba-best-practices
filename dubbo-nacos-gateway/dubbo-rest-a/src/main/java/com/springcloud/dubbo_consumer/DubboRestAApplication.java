package com.springcloud.dubbo_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DubboRestAApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboRestAApplication.class, args);
    }

}
