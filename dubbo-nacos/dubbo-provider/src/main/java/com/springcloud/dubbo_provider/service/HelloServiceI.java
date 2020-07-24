package com.springcloud.dubbo_provider.service;

import com.springcloud.dubbo_api.service.HelloService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;


@Component
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        timeout = 1200000,
        interfaceClass = HelloService.class,
        actives = 50,
        retries = 3,
        loadbalance = "roundrobin"
)
public class HelloServiceI implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
