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
import com.yucong.core.base.vo.BaseVO;
import com.yucong.core.base.vo.CommonVO;
import com.yucong.core.util.BeanMapper;
import com.yucong.core.util.MenuUtils;
import com.yucong.dto.menu.AddMenuDTO;
import com.yucong.dto.menu.DetailOrDeleteMenuDTO;
import com.yucong.dto.menu.ListMenuByRoleIdDTO;
import com.yucong.dto.menu.UpdateMenuDTO;
import com.yucong.entity.MenuRole;
import com.yucong.entity.Permission;
import com.yucong.service.MenuRoleService;
import com.yucong.service.MenuService;
import com.yucong.vo.menu.MenuVO;

@Auth
@RestController
@RequestMapping("permission")
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
	@GetMapping("listAll")
	public CommonVO<List<MenuVO>> findAll() {
		
		List<Permission> listAll = menuService.list();
		List<Permission> list = new ArrayList<Permission>();
		for (Permission menu : listAll) {
			// if (menu.getState() == 1 && menu.getFlagDel() == 0) {
				list.add(menu);
			// }
		}
		List<MenuVO> listMenuVO = BeanMapper.mapList(list, MenuVO.class);
		List<MenuVO> data = MenuUtils.formatMenu(listMenuVO);
		return new CommonVO<List<MenuVO>>(data);
	}

	/**
	 * 添加菜单
	 * 
	 * @author YN
	 * @date   2019-4-22
	 */
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
	@PostMapping("update")
	public CommonVO<Object> updateMenu(@Valid @RequestBody UpdateMenuDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return new CommonVO<Object>(menuService.updateMenu(dto,userId));
	}

	/**
	 * 通过主键id查询菜单详情
	 * 
	 * @author YN
	 * @date:  2019-4-22
	 */
	@GetMapping("detail")
	public CommonVO<MenuVO> detailMenu(@Valid DetailOrDeleteMenuDTO dto, BindingResult result) {
		return new CommonVO<MenuVO>(menuService.detailMenu(dto.getId()));
	}

	/**
	 * 通过主键id逻辑删除删除数据
	 * 
	 * @author YN
	 * @date   2019-4-22
	 */
	@PostMapping("delete")
	public BaseVO deleteMenu(@Valid @RequestBody DetailOrDeleteMenuDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return menuService.deleteMenu(dto.getId(),userId);
	}

	/**
	 * 返回的菜单数据需要以树形结构展示
	 * 
	 * @author YN
	 * @date   2019-4-22
	 */
	@GetMapping("listMenuByRoleId")
	public CommonVO<List<MenuVO>> ListMenuByRoleId(ListMenuByRoleIdDTO dto) {
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
		List<MenuVO> sysMenuVOList = new ArrayList<MenuVO>();
		if(!CollectionUtils.isEmpty(listAll)) {
			for(Permission sysMenu : listAll) {
				MenuVO sysMenuVO = new MenuVO();
				sysMenuVO.setId(sysMenu.getId());
				sysMenuVO.setParentId(sysMenu.getParentId());
				////sysMenuVO.setMenuName(sysMenu.getMenuName());
				////sysMenuVO.setMenuPath(sysMenu.getMenuPath());
				////sysMenuVO.setMenuSort(sysMenu.getMenuSort());
				//菜单设置成选中状态
				if(menuRoleIds.contains(sysMenuVO.getId())) {
					sysMenuVO.setChecked("true");//TODO
				} 
				sysMenuVOList.add(sysMenuVO);
			}
		}
		
		//生成树形结构数据
		List<MenuVO> data = MenuUtils.formatMenu(sysMenuVOList);
		return new CommonVO<List<MenuVO>>(data);
	}
	
	


	/**
	 * 如果是超级管理员，则拥有所有菜单权限
	 * 
	 * @author YN
	 * @date   2019-4-23
	 */ 
	private CommonVO<List<MenuVO>> getAllMenus(List<Permission> listAll) {
		List<MenuVO> menuVOs = BeanMapper.mapList(listAll, MenuVO.class);
		for(MenuVO menuVO : menuVOs) {
			menuVO.setChecked("true");
		}
		
		List<MenuVO> data = MenuUtils.formatMenu(menuVOs);
		return new CommonVO<List<MenuVO>>(data);
	}


}
