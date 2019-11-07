package com.yucong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yucong.BaseMapper;
import com.yucong.entity.RolePermission;

@Mapper
public interface MenuRoleMapper extends BaseMapper<RolePermission>{
    
	List<RolePermission> findByRoleIds(@Param("roleIds") List<Long> roleIds);

	List<RolePermission> findMenuRoleByRoleId(@Param("roleId") Long roleId);

	int deleteByRoleId(Long roleId);
}