package com.yucong.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yucong.BaseMapper;
import com.yucong.entity.Permission;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    Permission createResource(Permission resource);
    
    Permission updateResource(Permission resource);

    void deleteResource(Long resourceId);

    Permission findOne(Long resourceId);
    
    List<Permission> findAll();
    
    
    /**
     * 根据角色ID获取权限集合
     */
	Set<String> findByRoleIds(@Param("roleIds") List<Long> roleIds);

}
