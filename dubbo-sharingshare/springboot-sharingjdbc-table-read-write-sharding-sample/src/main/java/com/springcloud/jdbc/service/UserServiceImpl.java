package com.springcloud.jdbc.service;

import java.util.List;

import com.springcloud.jdbc.po.User;
import com.springcloud.jdbc.repository.UserRepository;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> list() {
        // 强制路由主库
        //HintManager.getInstance().setMasterRouteOnly();
        return userRepository.list();
    }
    @Override
    public Long add(User user) {
        return userRepository.addUser(user);
    }

}

