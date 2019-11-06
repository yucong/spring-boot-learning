package com.yucong.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yucong.core.base.service.BaseService;
import com.yucong.core.base.vo.BaseVO;
import com.yucong.core.base.vo.DataTableVO;
import com.yucong.core.util.BeanMapper;
import com.yucong.dto.role.AddMenuRoleDTO;
import com.yucong.dto.role.UpdateMenuRoleDTO;
import com.yucong.entity.MenuRole;
import com.yucong.entity.Role;
import com.yucong.mapper.MenuRoleMapper;
import com.yucong.mapper.RoleMapper;
import com.yucong.vo.role.RoleVO;

@Service
public class RoleService extends BaseService<Role, RoleMapper> {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Override
	public RoleMapper getMapper() {
		return roleMapper;
	}
    
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
    
    /**
	 * 添加一个角色，同时可添加这个角色的菜单权限
	 * 需注意的一个参数menuIds，表示这个角色的菜单权限的id集合
	 * 
	 * @author YN
	 * @date   2019-4-22
	 */
	public BaseVO addRoleMenu(AddMenuRoleDTO dto,Long userId) {
		Role record = new Role();
		record.setDescription(dto.getRoleDesc());
		record.setRole(dto.getRoleName());
		super.add(record,userId);
		if(dto.getMenuIds().size() > 0) {
			for(String menuId : dto.getMenuIds()) {
				MenuRole menuRole = new MenuRole();
				menuRole.setPermissionId(Long.parseLong(menuId));
				menuRole.setRoleId(record.getId());
				menuRoleMapper.insertSelective(menuRole);
			}
		}
		return new BaseVO();
	}

	public BaseVO updateRolePermission(UpdateMenuRoleDTO dto, Long userId) {
		Role record = new Role();
		record.setId(dto.getRoleId());
		record.setDescription(dto.getRoleDesc());
		record.setRole(dto.getRoleName());
		super.update(record,userId);
		
		menuRoleMapper.deleteByRoleId(dto.getRoleId());
		
		if(dto.getMenuIds().size() > 0) {
			for(String menuId : dto.getMenuIds()) {
				MenuRole menuRole = new MenuRole();
				menuRole.setPermissionId(Long.parseLong(menuId));
				menuRole.setRoleId(record.getId());
				menuRoleMapper.insertSelective(menuRole);
			}
		}
		return new BaseVO();
	}

	
}
