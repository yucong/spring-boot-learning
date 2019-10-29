package com.yucong.service;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yucong.entity.Permission;
import com.yucong.mapper.PermissionMapper;
import com.yucong.mapper.RolePermissionMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionDao;
    //@Autowired
    //private RolePermissionMapper rolePermissionMapper;

    public Permission createResource(Permission resource) {
        return permissionDao.createResource(resource);
    }

    public Permission updateResource(Permission resource) {
        return permissionDao.updateResource(resource);
    }

    public void deleteResource(Long resourceId) {
        permissionDao.deleteResource(resourceId);
    }

    public Permission findOne(Long resourceId) {
        return permissionDao.findOne(resourceId);
    }

    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    public Set<String> findPermissions(Set<Long> permissionIds) {
        Set<String> permissions = new HashSet<String>();
        for(Long permissionId : permissionIds) {
            Permission rermission = findOne(permissionId);
            if(rermission != null && !StringUtils.isEmpty(rermission.getPermission())) {
                permissions.add(rermission.getPermission());
            }
        }
        return permissions;
    }

    public List<Permission> findMenus(Set<String> permissions) {
        List<Permission> allResources = findAll();
        List<Permission> menus = new ArrayList<Permission>();
        for(Permission resource : allResources) {
            /*if(resource.isRootNode()) {
                continue;
            }
            if(resource.getType() != Resource.ResourceType.menu) {
                continue;
            }*/
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, Permission resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

}
