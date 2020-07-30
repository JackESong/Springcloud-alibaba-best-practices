package com.springcloud.dubbo_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MqKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqKafkaApplication.class, args);
    }

}
