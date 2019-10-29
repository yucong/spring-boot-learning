package com.yucong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yucong.BaseMapper;
import com.yucong.entity.Resource;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    Resource createResource(Resource resource);
    
    Resource updateResource(Resource resource);

    void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    
    List<Resource> findAll();

}
