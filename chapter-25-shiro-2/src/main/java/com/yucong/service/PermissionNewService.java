package com.yucong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yucong.core.base.service.BaseService;
import com.yucong.core.base.vo.BaseVO;
import com.yucong.core.util.BeanMapper;
import com.yucong.dto.menu.AddMenuDTO;
import com.yucong.dto.menu.UpdateMenuDTO;
import com.yucong.entity.MenuRole;
import com.yucong.entity.Permission;
import com.yucong.entity.UserRole;
import com.yucong.mapper.PermissionMapper;
import com.yucong.mapper.MenuRoleMapper;
import com.yucong.mapper.UserRoleMapper;
import com.yucong.vo.menu.PermissionVO;

import tk.mybatis.mapper.entity.Example;

@Service
public class PermissionNewService extends BaseService<Permission, PermissionMapper> {

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private MenuRoleMapper menuRoleMapper;

	@Override
	public PermissionMapper getMapper() {
		return permissionMapper;
	}

	/**
	 * 查询用户菜单数据
	 * 
	 * @author YN
	 * @date 2019-04-22
	 * 
	 */
	public List<MenuRole> findByUserId(long userId) {
		UserRole query = new UserRole();
		query.setUserId(userId);
		/// query.setState(StateEnum.VALID.getCode());
		List<UserRole> userRoles = userRoleMapper.select(query);
		List<Long> roleIds = new ArrayList<>();
		roleIds.add(0L);// 防止roleIds为空，查询出异常
		for (UserRole userRole : userRoles) {
			roleIds.add(userRole.getRoleId());
		}
		return menuRoleMapper.findByRoleIds(roleIds);
	}

	/**
	 * 查询所有权限数据
	 * 
	 * @author YN
	 * @date 2019-04-22
	 * 
	 */
	public List<Permission> list() {
		
		Example example = new Example(Permission.class);
		example.createCriteria()
			.andEqualTo("available", true);
		
		/// menu.setState(StateEnum.VALID.getCode());
		/// menu.setFlagDel(FlagDelEnum.NO.getCode());
		return permissionMapper.selectByExample(example);
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
	 * 
	 * @author YN
	 * @date 2019-4-22
	 */
	public Object addMenu(AddMenuDTO dto, Long userId) {
		Permission menu = BeanMapper.map(dto, Permission.class);
		menu.setAvailable(true);
		return super.add(menu, userId);
	}

	/**
	 * 更新菜单
	 * 
	 * @author YN
	 * @date 2019-4-22
	 */
	public Object updateMenu(UpdateMenuDTO dto, Long userId) {
		Permission menu = BeanMapper.map(dto, Permission.class);
		return super.update(menu, userId);
	}

	/**
	 * 通过主键id查询菜单详情
	 * 
	 * @author YN
	 * @date: 2019-4-22
	 */
	public PermissionVO detailMenu(int id) {
		Permission menu = super.detail(id);
		PermissionVO menuVO = BeanMapper.map(menu, PermissionVO.class);
		return menuVO;
	}

	/**
	 * 通过主键id逻辑删除删除数据
	 * 
	 * @author YN
	 * @date 2019-4-22
	 */
	public BaseVO deleteMenu(int id, Long userId) {
		Permission menu = super.detail(id);
		// 此菜单为父菜单时，删除所有的子菜单
		if (menu.getParentId() == 0) {
			Permission menuSon = new Permission();
			/// menuSon.setState(StateEnum.INVALID.getCode());
			/// menuSon.setFlagDel(FlagDelEnum.YES.getCode());
			Example example = new Example(Permission.class);
			example.createCriteria().andEqualTo("parentId", id);
			permissionMapper.updateByExampleSelective(menuSon, example);
		}
		/// menu.setState(StateEnum.INVALID.getCode());
		/// menu.setFlagDel(FlagDelEnum.YES.getCode());
		super.update(menu, userId);
		return new BaseVO();
	}

	/**
	 * 获取系统管理员默认菜单(菜单ID为-1的菜单)
	 * 
	 * @author 喻聪
	 * @date 2019-05-01
	 */
	public List<Permission> findAdminDefaultMenu() {
		Example example = new Example(Permission.class);
		/*example.createCriteria().andEqualTo("state", StateEnum.VALID.getCode())
				.andEqualTo("flagDel", FlagDelEnum.NO.getCode()).andLessThan("id", 0);*/
		example.createCriteria().andLessThan("id", 0);
		return permissionMapper.selectByExample(example);
	}

	/**
	 * 获取企业的全部菜单
	 * 
	 * @author 喻聪
	 * @date 2019-05-01
	 */
	public List<Permission> findEnterpriseMenu() {
		Example example = new Example(Permission.class);
		/*example.createCriteria().andEqualTo("state", StateEnum.VALID.getCode())
				.andEqualTo("flagDel", FlagDelEnum.NO.getCode()).andGreaterThan("id", 0);*/
		example.createCriteria().andGreaterThan("id", 0);
		return permissionMapper.selectByExample(example);
	}

}
