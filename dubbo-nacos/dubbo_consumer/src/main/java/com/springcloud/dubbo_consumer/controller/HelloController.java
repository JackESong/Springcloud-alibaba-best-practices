package com.springcloud.dubbo_consumer.controller;

import com.springcloud.dubbo_api.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @Reference
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        return helloService.hello("Dubbo!");
    }
}
