package com.yucong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yucong.BaseMapper;
import com.yucong.entity.Role;

@Mapper
public interface RoleMapper  extends BaseMapper<Role> {

	
	/**
	 * 根据用户ID获取角色
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> findByUserIdAndAvailableTrue(Long userId);
	
	
	/**
	 * 根据用户ID获取角色
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> findByUserId(Long userId);
	
	
	List<Long> findUserIdByRoleId(Long roleId);
	
	
}
