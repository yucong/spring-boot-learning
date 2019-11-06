//package com.crm.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.crm.annotations.ApiVersion;
//import com.crm.annotations.Auth;
//import com.crm.dto.menu.ListMenuBySysRoleIdDTO;
//import com.crm.dto.role.sys.AddSysMenuRoleDTO;
//import com.crm.dto.role.sys.ListSysRoleDTO;
//import com.crm.dto.role.sys.SysRoleIdDTO;
//import com.crm.dto.role.sys.SysUpdateMenuRoleDTO;
//import com.crm.entity.Menu;
//import com.crm.entity.SysMenuRole;
//import com.crm.service.MenuService;
//import com.crm.service.SysMenuRoleService;
//import com.crm.service.SysRoleService;
//import com.crm.util.MenuUtils;
//import com.crm.vo.BaseVO;
//import com.crm.vo.CommonVO;
//import com.crm.vo.DataTableVO;
//import com.crm.vo.MenuVO;
//import com.crm.vo.SysRoleVO;
//
//@Auth
//@RestController
//@RequestMapping("{version}/sysRole")
//public class SysRoleController {
//
//	@Autowired
//	private SysRoleService sysRoleService;
//	@Autowired
//	private SysMenuRoleService sysMenuRoleService;
//	@Autowired
//	private MenuService menuService;
//	
//	/**
//	 * 获取系统的所有角色模板
//	 * 
//	 * @author 喻聪
//	 * @date   2019-5-2
//	 */
//	@GetMapping("findAll")
//	public CommonVO<DataTableVO<SysRoleVO>> findAll(ListSysRoleDTO dto) {
//		return new CommonVO<>(sysRoleService.findAll(dto));
//	}
//	
//	/**
//	 * 添加一个系统角色模板，同时可添加这个角色的菜单权限
//	 * （需注意的一个参数menuIds，表示这个角色的菜单权限的id集合）
//	 * 
//	 * @author 喻聪
//	 * @date   2019-05-02
//	 */
//	@PostMapping("addRoleMenu")
//	public BaseVO addOrUpdateRole(@RequestBody AddSysMenuRoleDTO dto,BindingResult result,
//			@RequestHeader("X-User-Id") Integer userId) {
//		return sysRoleService.addRoleMenu(dto,userId);
//	}
//	
//	/**
//	 * 
//	 * 修改系统角色，需要修改对应的角色菜单
//	 * 
//	 * @author 喻聪
//	 * @date   2019-05-02
//	 */
//	@PostMapping("updateRoleMenu")
//	public BaseVO updateRoleMenu(@RequestBody @Valid SysUpdateMenuRoleDTO dto,BindingResult result,
//			@RequestHeader("X-User-Id") Integer userId) {
//		return sysRoleService.updateRoleMenu(dto,userId);
//	}
//	
//	/**
//	 * 逻辑删除该角色
//	 * 
//	 * @author 喻聪
//	 * @date   2019-05-02
//	 */
//	@PostMapping("deleteRoleMenu")
//	public BaseVO deleteRoleMenu(@RequestBody @Valid SysRoleIdDTO dto,BindingResult result,
//			@RequestHeader("X-User-Id") Integer userId) {
//		return sysRoleService.deleteRoleMenu(dto.getSysRoleId(),userId);
//	}
//	
//	/**
//	 * 
//	 * 根据系统模板角色ID获取该角色的所有菜单：以树形结构展示
//	 * 
//	 * @author 喻聪
//	 * @date   2019-05-02
//	 */
//	@ApiVersion(1)
//	@GetMapping("listMenuBySysRoleId")
//	public CommonVO<List<MenuVO>> listMenuBySysRoleId(ListMenuBySysRoleIdDTO dto) {
//		List<Menu> listAll = menuService.findEnterpriseMenu();
//		//1所有的有效菜单
//		List<Menu> listMenu = new ArrayList<Menu>();
//		for (Menu menu : listAll) {
//			if (menu.getState() == 1 && menu.getFlagDel() == 0) {
//				listMenu.add(menu);
//			}
//		}
//
//		//2该角色下的所有 菜单角色 集合
//		List<SysMenuRole> sysMenuRoles = sysMenuRoleService.findMenuRoleByRoleId(dto.getSysRoleId());
//
//		// 3整理出角色下的菜单Id集合
//		List<Integer> menuSysRoleIds = new ArrayList<>();
//		if (!CollectionUtils.isEmpty(sysMenuRoles)) {
//			for (SysMenuRole sysMenuRole : sysMenuRoles) {
//				menuSysRoleIds.add(sysMenuRole.getMenuId());
//			}
//		}
//
//		// 4 整理出角色拥有的菜单集合
//		List<MenuVO> sysMenuVOList = new ArrayList<>();
//		if(!CollectionUtils.isEmpty(listMenu)) {
//			for(Menu sysMenu : listMenu) {
//				MenuVO sysMenuVO = new MenuVO();
//				sysMenuVO.setId(sysMenu.getId());
//				sysMenuVO.setParentId(sysMenu.getParentId());
//				sysMenuVO.setMenuName(sysMenu.getMenuName());
//				sysMenuVO.setMenuPath(sysMenu.getMenuPath());
//				sysMenuVO.setMenuSort(sysMenu.getMenuSort());
//				//菜单设置成选中状态
//				if(menuSysRoleIds.contains(sysMenuVO.getId())) {
//					sysMenuVO.setChecked("true");//TODO
//				} 
//				sysMenuVOList.add(sysMenuVO);
//			}
//		}
//		
//		//生成树形结构数据
//		List<MenuVO> data = MenuUtils.formatMenu(sysMenuVOList);
//		return new CommonVO<List<MenuVO>>(data);
//	}
//}
