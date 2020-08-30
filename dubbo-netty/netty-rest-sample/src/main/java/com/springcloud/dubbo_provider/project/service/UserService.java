package com.springcloud.dubbo_provider.project.service;


import com.springcloud.dubbo_provider.project.model.User;

import java.util.List;

public interface UserService {

    List<User> list();

    Long add(User user);

}
