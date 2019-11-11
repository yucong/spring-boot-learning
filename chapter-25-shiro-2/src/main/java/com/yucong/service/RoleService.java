package com.yucong.service;

import java.util.ArrayList;
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
import com.yucong.core.shiro.ShiroKit;
import com.yucong.core.util.StringUtil;
import com.yucong.dto.role.AddMenuRoleDTO;
import com.yucong.dto.role.UpdateMenuRoleDTO;
import com.yucong.entity.Role;
import com.yucong.entity.RolePermission;
import com.yucong.mapper.MenuRoleMapper;
import com.yucong.mapper.RoleMapper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

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
    
    public DataTableVO<Role> list(String roleName,Boolean available,Integer pageNum,Integer pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	
    	Example example = new Example(Role.class);
    	Criteria criteria = example.createCriteria();
    	
    	if(available != null) {
    		criteria.andEqualTo("available", available);
    	}
    	if(StringUtil.isNotEmpty(roleName)) {
    		criteria.andLike("role", "%" + roleName + "%");
    	}
    	
		List<Role> entitys = roleMapper.selectByExample(example);
		PageInfo<Role> page = new PageInfo<>(entitys);
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		return new DataTableVO<Role>(pageSize, allCount, allPage, currentPage, entitys);
    }
    
    public List<Role> listByUserId(Long userId) {
    	return roleMapper.findByUserId(userId);
	}
    
    
    public List<Long> findRoleIdByUserId(Long userId) {
    	
    	List<Role> roles = roleMapper.findByUserId(userId);
    	List<Long> roleIds = new ArrayList<>();
    	for(Role role : roles) {
    		roleIds.add(role.getId());
    	}
    	return roleIds;
    }
    
    public Role findById(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    
    
    

    public Set<String> findRoles(List<Long> roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findById(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    /**
	 * 添加一个角色，同时可添加这个角色的菜单权限
	 * 需注意的一个参数menuIds，表示这个角色的菜单权限的id集合
	 * 
	 */
	public BaseVO addRoleMenu(AddMenuRoleDTO dto,Long userId) {
		Role record = new Role();
		record.setDescription(dto.getRoleDesc());
		record.setRole(dto.getRoleName());
		record.setAvailable(true);
		super.add(record,userId);
		if(dto.getMenuIds().size() > 0) {
			for(String menuId : dto.getMenuIds()) {
				RolePermission menuRole = new RolePermission();
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
				RolePermission menuRole = new RolePermission();
				menuRole.setPermissionId(Long.parseLong(menuId));
				menuRole.setRoleId(record.getId());
				menuRoleMapper.insertSelective(menuRole);
			}
		}
		return new BaseVO();
	}


	public void lockedRole(Long roleId) {
		
		Role role = roleMapper.selectByPrimaryKey(roleId);
    	role.setAvailable(!role.getAvailable());
    	roleMapper.updateByPrimaryKeySelective(role);
        
    	
        // 冻结角色，需要清除缓存
    	List<Long> userIds = roleMapper.findUserIdByRoleId(roleId);
        ShiroKit.reloadAuthorizing(userIds);
		
	}

	

	
}
