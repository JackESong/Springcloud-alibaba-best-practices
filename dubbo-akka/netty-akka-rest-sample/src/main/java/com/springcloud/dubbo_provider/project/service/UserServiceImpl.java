package com.springcloud.dubbo_provider.project.service;

import akka.actor.ActorRef;
import com.springcloud.dubbo_provider.project.model.User;
import com.springcloud.dubbo_provider.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

