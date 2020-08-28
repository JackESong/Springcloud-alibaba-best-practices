package com.springcloud.jdbc.repository;

import com.springcloud.jdbc.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository {

    Long addUser(User user);

    List<User> list();

    List<User> getAllOrderPresent();
}
