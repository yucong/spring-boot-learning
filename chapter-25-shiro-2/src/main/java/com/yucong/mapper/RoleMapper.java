package com.yucong.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yucong.BaseMapper;
import com.yucong.entity.Role;

@Mapper
public interface RoleMapper  extends BaseMapper<Role> {

    /*Role createRole(Role role);
    
    Role updateRole(Role role);
    
    void deleteRole(Long roleId);

    Role findOne(Long roleId);
    
    List<Role> findAll();*/

    /**
     * 根据角色ID获取权限集合
     */
	Set<String> findByRoleIds(@Param("roleIds") List<Long> roleIds);
}
