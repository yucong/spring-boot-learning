package com.yucong.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.core.annotion.CurrentUser;
import com.yucong.core.base.vo.CommonVO;
import com.yucong.core.enums.PermissionTypeEnum;
import com.yucong.core.util.MenuUtils;
import com.yucong.entity.Permission;
import com.yucong.service.PermissionService;
import com.yucong.service.RoleService;
import com.yucong.vo.menu.PermissionVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "05-主页")
@RestController
public class IndexController {

   /* @Autowired
    private PermissionService resourceService;
    @Autowired
    private UserService userService;*/

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RoleService roleService;
	
	
    @GetMapping("/index")
    public String index(@CurrentUser Long userId ) {
       /* Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<Permission> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);*/
        return "index" + userId;
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
    
    
    
    
    /**
	 * 获取用户主页菜单
	 */
	@ApiOperation(value="主页菜单")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
	@GetMapping(value = "listMenu")
	public CommonVO<List<PermissionVO>> listMenu(@ApiIgnore @CurrentUser Long userId) {
		
		// 1 获取该用户的角色
		List<Long> roleIds = roleService.findRoleIdByUserId(userId);
		
		// 2 获取用户的权限
		List<Permission> permissions = null;
		if(roleIds.isEmpty()) {
			permissions = new ArrayList<>();
		} else {
			permissions = permissionService.findByRoleIds(roleIds);
		}
		
		// 3 整理出角色拥有的菜单集合
		List<PermissionVO> permissionVOs = new ArrayList<PermissionVO>();
		if(!CollectionUtils.isEmpty(permissions)) {
			for(Permission permission : permissions) {
				if(permission.getType().equals(PermissionTypeEnum.menu.name())) {
					PermissionVO permissionVO = new PermissionVO();
					permissionVO.setId(permission.getId());
					permissionVO.setParentId(permission.getParentId());
					permissionVO.setName(permission.getName());
					permissionVO.setPermission(permission.getPermission());
					permissionVO.setSort(permission.getSort());
					permissionVOs.add(permissionVO);
				}
			}
		}
		
		// 4 生成树形结构数据
		List<PermissionVO> data = MenuUtils.formatMenu(permissionVOs);
		return new CommonVO<List<PermissionVO>>(data);
	}
	
	


}
