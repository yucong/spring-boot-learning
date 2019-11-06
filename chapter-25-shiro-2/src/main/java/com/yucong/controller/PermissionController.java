package com.yucong.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.core.annotion.Auth;
import com.yucong.core.annotion.CurrentUser;
import com.yucong.core.base.vo.BaseVO;
import com.yucong.core.base.vo.CommonVO;
import com.yucong.core.util.BeanMapper;
import com.yucong.core.util.MenuUtils;
import com.yucong.dto.menu.AddMenuDTO;
import com.yucong.dto.menu.PermissionIdDTO;
import com.yucong.dto.menu.ListMenuByRoleIdDTO;
import com.yucong.dto.menu.UpdateMenuDTO;
import com.yucong.entity.MenuRole;
import com.yucong.entity.Permission;
import com.yucong.service.MenuRoleService;
import com.yucong.service.MenuService;
import com.yucong.vo.menu.PermissionVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Auth
@RestController
@RequestMapping("permission")
@Api(tags = "03-权限管理")
public class PermissionController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private MenuRoleService menuRoleService;

	/**
	 * 查询所有菜单数据
	 * 以树形结构展示所有菜单数据
	 * 返回值中有一个checked字段，用于角色or用户模块时的选中状态
	 * 
	 * @author YN
	 * @date   2019-04-22
	 *         
	 */
	@ApiOperation(value="查询所有菜单")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
	@GetMapping("listAll")
	public CommonVO<List<PermissionVO>> findAll() {
		
		List<Permission> listAll = menuService.list();
		List<Permission> list = new ArrayList<Permission>();
		for (Permission permission : listAll) {
			// if (menu.getState() == 1 && menu.getFlagDel() == 0) {
				list.add(permission);
			// }
		}
		List<PermissionVO> listMenuVO = BeanMapper.mapList(list, PermissionVO.class);
		List<PermissionVO> data = MenuUtils.formatMenu(listMenuVO);
		return new CommonVO<List<PermissionVO>>(data);
	}

	/**
	 * 添加菜单
	 * 
	 * @author YN
	 * @date   2019-4-22
	 */
	@ApiOperation(value="添加权限")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
	@PostMapping("add")
	public CommonVO<Object> addMenu(@RequestBody @Valid AddMenuDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return new CommonVO<Object>(menuService.addMenu(dto,userId));
	}

	/**
	 * 更新菜单
	 * 
	 * @author YN
	 * @date   2019-4-22
	 */
	@ApiOperation(value="更新权限")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
	@PostMapping("update")
	public CommonVO<Object> updateMenu(@Valid @RequestBody UpdateMenuDTO dto, 
			@RequestHeader("X-User-Id") Integer userId) {
		return new CommonVO<Object>(menuService.updateMenu(dto,userId));
	}

	/**
	 * 通过主键id查询菜单详情
	 * 
	 * @author YN
	 * @date:  2019-4-22
	 */
	@ApiOperation(value="权限详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
	@GetMapping("detail")
	public CommonVO<PermissionVO> detailMenu(@Valid PermissionIdDTO dto) {
		return new CommonVO<PermissionVO>(menuService.detailMenu(dto.getId()));
	}

	/**
	 * 通过主键id逻辑删除删除数据
	 * 
	 * @author YN
	 * @date   2019-4-22
	 */
	@ApiOperation(value="删除权限")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
	@PostMapping("delete")
	public BaseVO deleteMenu(@Valid @RequestBody PermissionIdDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return menuService.deleteMenu(dto.getId(),userId);
	}

	/**
	 * 返回的菜单数据需要以树形结构展示
	 * 
	 * @author YN
	 * @date   2019-4-22
	 */
	@ApiOperation(value="根据角色查询权限")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
	@GetMapping("listPermissionByRoleId")
	public CommonVO<List<PermissionVO>> ListMenuByRoleId(ListMenuByRoleIdDTO dto) {
		//1所有的有效菜单
		List<Permission> listAll = menuService.findEnterpriseMenu();

		//如果该角色是超级管理员
		if(dto.getRoleId() == -1) {
			return getAllMenus(listAll);
		}
		
		//2该角色下的所有 菜单角色 集合
		List<MenuRole> listMenuRole = menuRoleService.findMenuRoleByRoleId(dto.getRoleId());

		// 3整理出角色下的菜单Id集合
		List<Long> menuRoleIds = new ArrayList<>();
		if (!CollectionUtils.isEmpty(listMenuRole)) {
			for (MenuRole sysMenuRole : listMenuRole) {
				menuRoleIds.add(sysMenuRole.getMenuId());
			}
		}

		// 4 整理出角色拥有的菜单集合
		List<PermissionVO> sysMenuVOList = new ArrayList<PermissionVO>();
		if(!CollectionUtils.isEmpty(listAll)) {
			for(Permission sysMenu : listAll) {
				PermissionVO sysMenuVO = new PermissionVO();
				sysMenuVO.setId(sysMenu.getId());
				sysMenuVO.setParentId(sysMenu.getParentId());
				sysMenuVO.setName(sysMenu.getName());
				sysMenuVO.setPermission(sysMenu.getPermission());
				sysMenuVO.setSort(sysMenu.getSort());
				//菜单设置成选中状态
				if(menuRoleIds.contains(sysMenuVO.getId())) {
					sysMenuVO.setChecked("true");//TODO
				} 
				sysMenuVOList.add(sysMenuVO);
			}
		}
		
		//生成树形结构数据
		List<PermissionVO> data = MenuUtils.formatMenu(sysMenuVOList);
		return new CommonVO<List<PermissionVO>>(data);
	}
	
	


	/**
	 * 如果是超级管理员，则拥有所有菜单权限
	 * 
	 * @author YN
	 * @date   2019-4-23
	 */ 
	private CommonVO<List<PermissionVO>> getAllMenus(List<Permission> listAll) {
		List<PermissionVO> menuVOs = BeanMapper.mapList(listAll, PermissionVO.class);
		for(PermissionVO menuVO : menuVOs) {
			menuVO.setChecked("true");
		}
		
		List<PermissionVO> data = MenuUtils.formatMenu(menuVOs);
		return new CommonVO<List<PermissionVO>>(data);
	}
	
	
	
	/**
	 * 根据用户id获取该用户所有菜单权限
	 * 需要先获取用户的角色集合，再通过角色获取菜单权限
	 * 
	 * @author YN
	 * @date   2019-4-23
	 */
	@ApiOperation(value="我的主页菜单")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
	@GetMapping(value = "listMyMenu")
	public CommonVO<List<PermissionVO>> listMyMenu(@ApiIgnore @CurrentUser Long userId) {
		
		// User u = userService.detail(userId);
		
		// 超级管理员
		// if( userId == -1) {
			return getSuperAdminDefautMenu();
		// }
	}
	
	/*
	 * 超级管理员获取系统默认菜单
	 * 
	 * @author 喻聪
	 * @date   2019-05-01
	 */
	private CommonVO<List<PermissionVO>> getSuperAdminDefautMenu() {
		
		// 1 获取所有有效菜单
		List<Permission> adminMenus = menuService.listMenu();
		List<PermissionVO> sysMenuVOList = BeanMapper.mapList(adminMenus, PermissionVO.class);

		// 2 生成树形结构
		List<PermissionVO> data = MenuUtils.formatMenu(sysMenuVOList);
		CommonVO<List<PermissionVO>> commonVO = new CommonVO<>();
		commonVO.setData(data);
		return commonVO;
	}


}
