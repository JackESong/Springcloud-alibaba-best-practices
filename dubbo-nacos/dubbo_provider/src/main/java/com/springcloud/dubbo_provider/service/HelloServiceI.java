package com.springcloud.dubbo_provider.service;

import com.springcloud.dubbo_api.service.HelloService;
import org.apache.dubbo.config.annotation.Service;


@Service
public class HelloServiceI implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
