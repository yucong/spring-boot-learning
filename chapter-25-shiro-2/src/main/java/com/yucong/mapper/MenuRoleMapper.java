package com.yucong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yucong.BaseMapper;
import com.yucong.entity.MenuRole;

@Mapper
public interface MenuRoleMapper extends BaseMapper<MenuRole>{
    
	List<MenuRole> findByRoleIds(@Param("roleIds") List<Long> roleIds);

	List<MenuRole> findMenuRoleByRoleId(@Param("roleId") Long roleId);

	int deleteByRoleId(Long roleId);
}