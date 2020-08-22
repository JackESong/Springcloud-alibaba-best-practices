package com.springcloud.jdbc.service;

import com.springcloud.jdbc.po.User;

import java.util.List;

public interface UserService {

    List<User> list();

    Long add(User user);

}
