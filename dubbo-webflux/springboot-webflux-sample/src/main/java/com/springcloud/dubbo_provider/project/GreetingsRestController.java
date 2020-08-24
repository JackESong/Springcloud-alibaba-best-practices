package com.springcloud.dubbo_provider.project;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class GreetingsRestController {


   @GetMapping(value = "/ask")
    public String greetStream() {
        return "123";
    }


}
