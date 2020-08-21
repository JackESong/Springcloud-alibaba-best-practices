package com.springcloud.jdbc.controller;

import com.springcloud.jdbc.po.User;
import com.springcloud.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Object list() {
        return userService.list();
    }

    @GetMapping("/add")
    public void add() {
        for (long i = 0; i < 100; i++) {
            User user = new User();
            user.setId(i);
            user.setCity("深圳");
            user.setName("李四");
            userService.add(user);
        }
    }

}
