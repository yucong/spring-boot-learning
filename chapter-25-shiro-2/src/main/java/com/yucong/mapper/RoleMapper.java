package com.yucong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yucong.BaseMapper;
import com.yucong.entity.Role;

@Mapper
public interface RoleMapper  extends BaseMapper<Role> {

    Role createRole(Role role);
    
    Role updateRole(Role role);
    
    void deleteRole(Long roleId);

    Role findOne(Long roleId);
    
    List<Role> findAll();
}
