package com.yucong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yucong.core.base.service.BaseService;
import com.yucong.core.base.vo.BaseVO;
import com.yucong.core.enums.PermissionTypeEnum;
import com.yucong.core.shiro.ShiroKit;
import com.yucong.core.util.BeanMapper;
import com.yucong.dto.menu.AddMenuDTO;
import com.yucong.dto.menu.UpdateMenuDTO;
import com.yucong.entity.Permission;
import com.yucong.entity.RolePermission;
import com.yucong.entity.UserRole;
import com.yucong.mapper.PermissionMapper;
import com.yucong.mapper.RoleMapper;
import com.yucong.mapper.MenuRoleMapper;
import com.yucong.mapper.UserRoleMapper;
import com.yucong.vo.menu.PermissionVO;

import tk.mybatis.mapper.entity.Example;

@Service
public class PermissionService extends BaseService<Permission, PermissionMapper> {

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private MenuRoleMapper menuRoleMapper;

	@Override
	public PermissionMapper getMapper() {
		return permissionMapper;
	}
	
	/**
	 * 查询所有权限数据
	 */
	public List<Permission> listAll() {
		return permissionMapper.selectAll();
	}
	
	/**
	 * 查询用户菜单数据
	 */
	public List<RolePermission> findByUserId(long userId) {
		UserRole query = new UserRole();
		query.setUserId(userId);
		List<UserRole> userRoles = userRoleMapper.select(query);
		List<Long> roleIds = new ArrayList<>();
		roleIds.add(0L);// 防止roleIds为空，查询出异常
		for (UserRole userRole : userRoles) {
			roleIds.add(userRole.getRoleId());
		}
		return menuRoleMapper.findByRoleIds(roleIds);
	}
	
	/**
	 * 查询所有有效菜单
	 */
	public List<Permission> listMenu() {
		Example example = new Example(Permission.class);
		example.createCriteria()
			.andEqualTo("type", "menu")
			.andEqualTo("available", true);
		return permissionMapper.selectByExample(example);
	}

	/**
	 * 添加菜单
	 */
	public Object addMenu(AddMenuDTO dto, Long userId) {
		Permission menu = BeanMapper.map(dto, Permission.class);
		menu.setAvailable(true);
		return super.add(menu, userId);
	}

	/**
	 * 更新菜单
	 */
	public Object updateMenu(UpdateMenuDTO dto, Long userId) {
		Permission menu = BeanMapper.map(dto, Permission.class);
		return super.update(menu, userId);
	}

	/**
	 * 通过主键id查询菜单详情
	 */
	public PermissionVO detailMenu(Long id) {
		Permission menu = super.detail(id);
		PermissionVO menuVO = BeanMapper.map(menu, PermissionVO.class);
		return menuVO;
	}

	/**
	 * 冻结或解除权限，只针对按钮或菜单才能操作，父菜单不允许操作
	 * 
	 * @author YN
	 * @date 2019-4-22
	 */
	public BaseVO locked(Long id, Long userId) {
		Permission menu = super.detail(id);
		boolean target = !menu.getAvailable();
		
		BaseVO result = new BaseVO();
		
		if(PermissionTypeEnum.menu.name().equals( menu.getType() ) ) {
			
			if(menu.getParentId() > 0) {
				// 冻结子菜单
				menu.setAvailable(target);
				super.update(menu, userId);
				
				// 冻结子菜单关联的按钮
				Permission menuSon = new Permission();
				menuSon.setAvailable(target);
				Example example = new Example(Permission.class);
				example.createCriteria().andEqualTo("parentId", id);
				permissionMapper.updateByExampleSelective(menuSon, example);
			} else {
				result.setCode(0);
				result.setMessage("一级菜单不支持此操作");
			}
			
		} else if(PermissionTypeEnum.button.name().equals( menu.getType() )) {
			
			// 解除按钮时，子菜单必须是有效的
			Permission parent = permissionMapper.selectByPrimaryKey(menu.getParentId());
			if(parent != null && parent.getAvailable() ) {
				menu.setAvailable(target);
				super.update(menu, userId);
			} else {
				result.setCode(0);
				result.setMessage("必须先解冻关联菜单");
			}
		}
		
		
		
		// 冻结权限时，对应的角色发生了变化，角色关联的用户获取到权限发生了变化，所以需要清除用户的缓存
		List<Long> roleIds = roleMapper.findRoleIdByPermissionId(id);
		if(!roleIds.isEmpty()) {
			List<Long> userIds = roleMapper.findUserIdByRoleIds(roleIds);
			if(!userIds.isEmpty()) {
				ShiroKit.reloadAuthorizing(userIds);
			}
		}
		return result;
	}

	/**
	 * 获取系统管理员默认菜单(菜单ID为-1的菜单)
	 */
	public List<Permission> findAdminDefaultMenu() {
		Example example = new Example(Permission.class);
		example.createCriteria().andLessThan("id", 0);
		return permissionMapper.selectByExample(example);
	}

	/**
	 * 获取企业的全部菜单
	 * 
	 * @author 喻聪
	 * @date 2019-05-01
	 */
	public List<Permission> findAvailablePermissions() {
		Permission permission = new Permission();
		permission.setAvailable(true);
		return permissionMapper.select(permission);
	}

	/**
	 * 根据角色Id数组获取权限集合
	 */
	public List<Permission> findByRoleIds(List<Long> roleIds) {
		return permissionMapper.findByRoleIds(roleIds);
	}

}
