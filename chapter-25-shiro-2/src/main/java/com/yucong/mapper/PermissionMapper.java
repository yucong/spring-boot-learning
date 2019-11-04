package com.yucong.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yucong.BaseMapper;
import com.yucong.entity.Permission;
import com.yucong.vo.menu.MenuVO;



@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

	List<MenuVO> ListMenuByRoleId(Integer roleId);
    
    
    /** 根据角色ID获取权限集合*/
	Set<String> findByRoleIds(@Param("roleIds") List<Long> roleIds);


}