package com.yucong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yucong.BaseMapper;
import com.yucong.entity.Permission;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    Permission createResource(Permission resource);
    
    Permission updateResource(Permission resource);

    void deleteResource(Long resourceId);

    Permission findOne(Long resourceId);
    
    List<Permission> findAll();

}
