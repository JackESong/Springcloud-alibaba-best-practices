package com.springcloud.dubbo_provider.project.repository;

import com.springcloud.dubbo_provider.project.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository {

    Long addUser(User user);

    List<User> list();

}
