package com.yucong.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yucong.entity.Role;
import com.yucong.mapper.RoleMapper;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleDao;
    @Autowired
    private PermissionService resourceService;

    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    public Role updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    public Role findOne(Long roleId) {
        return roleDao.findOne(roleId);
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }

    public Set<String> findRoles(List<Long> roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    public Set<String> Permission(Long[] roleIds) {
        Set<Long> pesourceIds = new HashSet<Long>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                // resourceIds.addAll(role.getResourceIds());
            }
        }
        // return resourceService.findPermissions(resourceIds);
        return null;
    }
}
