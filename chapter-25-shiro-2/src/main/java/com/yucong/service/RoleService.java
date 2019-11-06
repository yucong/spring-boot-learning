package com.yucong.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yucong.core.base.vo.DataTableVO;
import com.yucong.core.util.BeanMapper;
import com.yucong.entity.Role;
import com.yucong.mapper.RoleMapper;
import com.yucong.vo.role.RoleVO;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public void createRole(Role role) {
        roleMapper.insertSelective(role);
    }

    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    public void deleteRole(Long roleId) {
        roleMapper.deleteByPrimaryKey(roleId);
    }

    public Role findOne(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    public DataTableVO<RoleVO> findAll(int pageSize,int size) {
    	PageHelper.startPage(pageSize, size);
		List<Role> entitys = roleMapper.selectAll();
		PageInfo<Role> page = new PageInfo<>(entitys);
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		List<RoleVO> roleVOs = BeanMapper.mapList(entitys, RoleVO.class);
		return new DataTableVO<RoleVO>(pageSize, allCount, allPage, currentPage, roleVOs);
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
