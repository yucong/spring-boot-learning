package com.yucong.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yucong.BaseMapper;
import com.yucong.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /*User createUser(User user);
    
    User updateUser(User user);
    
    void deleteUser(Long userId);

    User findOne(Long userId);

    List<User> findAll();

    User findByUsername(String username);*/

}
