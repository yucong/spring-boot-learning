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

    public void createRole(Role role) {
        roleDao.insertSelective(role);
    }

    public void updateRole(Role role) {
        roleDao.updateByPrimaryKeySelective(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteByPrimaryKey(roleId);
    }

    public Role findOne(Long roleId) {
        return roleDao.selectByPrimaryKey(roleId);
    }

    public List<Role> findAll() {
        return roleDao.selectAll();
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

    public Set<String> findPermissions(List<Long> roleIds) {
        // return roleDao.findByRoleIds(roleIds);
    	return null;
    }
}
