package com.yucong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yucong.BaseMapper;
import com.yucong.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

	List<Long> findUsersByRoleId(Long roleId);

	
	
}
