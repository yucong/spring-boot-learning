package com.yucong.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yucong.entity.User;

import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    

}
