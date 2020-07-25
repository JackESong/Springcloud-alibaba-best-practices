package com.springcloud.dubbo_consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/docker")
    public String hello() {
        return "你好 ！";
    }
}
