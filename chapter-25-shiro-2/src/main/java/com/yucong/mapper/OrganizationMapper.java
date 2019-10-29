package com.yucong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yucong.BaseMapper;
import com.yucong.entity.Organization;

@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {

    Organization createOrganization(Organization organization);
    
    Organization updateOrganization(Organization organization);
    
    void deleteOrganization(Long organizationId);
    
    Organization findOne(Long organizationId);
    
    List<Organization> findAll();
    
    List<Organization> findAllWithExclude(Organization excludeOraganization);
    
    void move(Organization source, Organization target);
}
