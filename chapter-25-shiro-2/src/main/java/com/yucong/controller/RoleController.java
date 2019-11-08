package com.yucong.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.core.annotion.Auth;
import com.yucong.core.base.vo.BaseVO;
import com.yucong.core.base.vo.CommonVO;
import com.yucong.core.base.vo.DataTableVO;
import com.yucong.dto.role.AddMenuRoleDTO;
import com.yucong.dto.role.ListRoleDTO;
import com.yucong.dto.role.RoleIdDTO;
import com.yucong.dto.role.UpdateMenuRoleDTO;
import com.yucong.entity.Role;
import com.yucong.service.RoleService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


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
	public CommonVO<DataTableVO<Role>> list(ListRoleDTO dto) {
		return new CommonVO<>(roleService.list(dto.getRoleName(),dto.getAvailable(),dto.getPage(),dto.getSize()));
	}
	
	/**
	 * 查找所有的角色，分页展示，可模糊查询条件为roleName;
	 * 
	 * @author YN
	 * @date   2019-4-22
	 */
	@GetMapping("listByUserId")
	public CommonVO<List<Role>> listByUserId(Long userId) {
		return new CommonVO<>(roleService.listByUserId(userId));
	}

	
	/**
	 * 添加一个角色，同时可添加这个角色的菜单权限
	 * 需注意的一个参数menuIds，表示这个角色的菜单权限的id集合
	 * 
	 * @author YN
	 * @date   2019-4-22
	 */
	@PostMapping("addRolePermission")
	public BaseVO addRolePermission(@RequestBody @Valid AddMenuRoleDTO dto,@RequestHeader("X-User-Id") Long userId) {
		return roleService.addRoleMenu(dto,userId);
	}
	
	/**
	 * 修改一个角色，及该角色的菜单权限
	 * 需注意的一个参数menuIds，表示这个角色的菜单权限的id集合
	 * 
	 * @author  YN
	 * @date    2019-4-23
	 */
	@PostMapping("updateRolePermission")
	public BaseVO updateRolePermission(@RequestBody @Valid UpdateMenuRoleDTO dto, @RequestHeader("X-User-Id") Long userId) {
		return roleService.updateRolePermission(dto,userId);
	}
	
	
	@ApiOperation(value="冻结角色")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
    @PostMapping(value = "locked")
    public BaseVO locked(@RequestBody @Valid RoleIdDTO dto) {
		roleService.lockedRole(dto.getRoleId());
        return BaseVO.success();
    }
	
}
