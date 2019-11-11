package com.yucong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yucong.BaseMapper;
import com.yucong.entity.Role;

@Mapper
public interface RoleMapper  extends BaseMapper<Role> {

	
	/**
	 * 根据用户ID获取角色
	 */
	List<Role> findByUserIdAndAvailableTrue(Long userId);
	
	
	/**
	 * 根据用户ID获取角色
	 */
	List<Role> findByUserId(Long userId);
	
	
	List<Long> findUserIdByRoleId(Long roleId);
	
	List<Long> findUserIdByRoleIds(@Param("roleIds") List<Long> roleIds);
	
	List<Long> findRoleIdByPermissionId(Long permissionId);


	
}
