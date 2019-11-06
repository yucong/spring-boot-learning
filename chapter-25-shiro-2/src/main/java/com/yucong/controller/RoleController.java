package com.yucong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.core.annotion.Auth;
import com.yucong.core.base.vo.CommonVO;
import com.yucong.core.base.vo.DataTableVO;
import com.yucong.dto.role.ListRoleDTO;
import com.yucong.service.RoleService;
import com.yucong.vo.role.RoleVO;


@Auth
@RestController
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	/**
	 * 查找所有的角色，分页展示，可模糊查询条件为roleName;
	 * 
	 * @author YN
	 * @date   2019-4-22
	 */
	@GetMapping("list")
	public CommonVO<DataTableVO<RoleVO>> findAll(ListRoleDTO dto) {
		return new CommonVO<>(roleService.findAll(dto.getPage(),dto.getSize()));
	}
	
	/**
	 * 查询企业所有角色
	 * 
	 * @author 喻聪
	 * @date   2019-5-3
	 */
	/*@GetMapping("listSimple")
	public CommonVO<List<SimpleRoleVO>> listSimple(ListSimpleRoleDTO dto) {
		return new CommonVO<>(roleService.listSimple(dto.getEnterpriseId()));
	}*/
	
	/**
	 * 添加一个角色，同时可添加这个角色的菜单权限
	 * 需注意的一个参数menuIds，表示这个角色的菜单权限的id集合
	 * 
	 * @author YN
	 * @date   2019-4-22
	 */
	/*@PostMapping("addRoleMenu")
	public BaseVO addOrUpdateRole(@RequestBody @Valid AddMenuRoleDTO dto,BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return roleService.addRoleMenu(dto,userId);
	}*/
	
	/**
	 * 修改一个角色，及该角色的菜单权限
	 * 需注意的一个参数menuIds，表示这个角色的菜单权限的id集合
	 * 
	 * @author  YN
	 * @date    2019-4-23
	 */
	/*@PostMapping("updateRoleMenu")
	public BaseVO updateRoleMenu(@RequestBody @Valid UpdateMenuRoleDTO dto,BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return roleService.updateRoleMenu(dto,userId);
	}*/
	
	/**
	 * 
	 * 逻辑删除该角色
	 * 
	 * @author  YN
	 * @date    2019-4-23
	 */
	/*@PostMapping("deleteRoleMenu")
	public BaseVO deleteRoleMenu(@RequestBody @Valid RoleIdDTO dto,BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return roleService.deleteRoleMenu(dto.getRoleId(),userId);
	}*/
}
