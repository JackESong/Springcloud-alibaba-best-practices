package com.springcloud.dubbo_consumer.controller;

import com.springcloud.dubbo_api.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            check = false,
            timeout = 1200000,
            interfaceClass = HelloService.class)
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        return helloService.hello("dubbo-rest-b!");
    }
}
