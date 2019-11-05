package com.yucong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiOperation;


/**
 * 平台用户管理
 * 
 * @author 喻聪
 * @date   2018-02-06
 * 
 */
@RestController
@RequestMapping(value = "sys/user")
public class SysUserController  {
    
	/**
	 * 获取我的所有菜单
	 */
	@ApiOperation(value = "获取菜单")
    @GetMapping(value="listMyMenu")
	public JSONObject listMyMenu() {
		//String result = "{\"code\":1,\"message\":\"操作成功\",\"data\":[{\"id\":1,\"parentId\":0,\"menuName\":\"系统运行日志\",\"iconCls\":\"database\",\"menuSort\":1,\"menuPath\":\"\",\"children\":[{\"id\":4,\"parentId\":1,\"menuName\":\"请求接口日志\",\"iconCls\":\"\",\"menuSort\":1,\"menuPath\":\"module/api/http_request_log.html\",\"children\":[],\"checked\":null,\"memo\":null,\"scode\":\"\",\"itype\":1},{\"id\":5,\"parentId\":1,\"menuName\":\"服务异常日志\",\"iconCls\":\"\",\"menuSort\":2,\"menuPath\":\"module/api/server_exception_log.html\",\"children\":[],\"checked\":null,\"memo\":null,\"scode\":\"\",\"itype\":1},{\"id\":6,\"parentId\":1,\"menuName\":\"业务异常日志\",\"iconCls\":\"\",\"menuSort\":3,\"menuPath\":\"module/api/business_abnormal_log.html\",\"children\":[],\"checked\":null,\"memo\":null,\"scode\":\"\",\"itype\":1}],\"checked\":null,\"memo\":\"\",\"scode\":\"\",\"itype\":1},{\"id\":2,\"parentId\":0,\"menuName\":\"定时任务管理\",\"iconCls\":\"database\",\"menuSort\":2,\"menuPath\":\"\",\"children\":[{\"id\":3,\"parentId\":2,\"menuName\":\"任务列表\",\"iconCls\":\"\",\"menuSort\":1,\"menuPath\":\"module/task/task.html\",\"children\":[],\"checked\":null,\"memo\":null,\"scode\":\"\",\"itype\":1}],\"checked\":null,\"memo\":\"\",\"scode\":\"\",\"itype\":1}]}";
		// String result = "{\"code\":1,\"message\":\"操作成功\",\"data\":[{\"itype\":1,\"checked\":null,\"iconCls\":\"database\",\"id\":1,\"menuPath\":\"\",\"memo\":\"\",\"scode\":\"\",\"menuSort\":1,\"menuName\":\"系统运行日志\",\"children\":[{\"scode\":\"\",\"checked\":null,\"memo\":null,\"iconCls\":\"\",\"menuSort\":1,\"itype\":1,\"children\":[],\"parentId\":1,\"menuName\":\"请求接口日志\",\"id\":4,\"menuPath\":\"module/api/http_request_log.html\"},{\"itype\":1,\"menuName\":\"服务异常日志\",\"checked\":null,\"id\":5,\"memo\":null,\"children\":[],\"menuPath\":\"module/api/server_exception_log.html\",\"menuSort\":2,\"parentId\":1,\"scode\":\"\",\"iconCls\":\"\"},{\"menuPath\":\"module/api/business_abnormal_log.html\",\"id\":6,\"checked\":null,\"parentId\":1,\"memo\":null,\"menuName\":\"业务异常日志\",\"scode\":\"\",\"itype\":1,\"children\":[],\"menuSort\":3,\"iconCls\":\"\"}],\"parentId\":0}]}";
		String result = "{\"code\":1,\"message\":\"操作成功\",\"data\":[{\"itype\":1,\"checked\":null,\"iconCls\":\"database\",\"id\":1,\"menuPath\":\"\",\"memo\":\"\",\"scode\":\"\",\"menuSort\":1,\"menuName\":\"系统管理\",\"children\":[{\"scode\":\"\",\"checked\":null,\"memo\":null,\"iconCls\":\"\",\"menuSort\":1,\"itype\":1,\"children\":[],\"parentId\":1,\"menuName\":\"权限管理\",\"id\":4,\"menuPath\":\"module/system/sys_menu.html\"},{\"itype\":1,\"menuName\":\"角色管理\",\"checked\":null,\"id\":5,\"memo\":null,\"children\":[],\"menuPath\":\"module/api/server_exception_log.html\",\"menuSort\":2,\"parentId\":1,\"scode\":\"\",\"iconCls\":\"\"},{\"menuPath\":\"module/api/business_abnormal_log.html\",\"id\":6,\"checked\":null,\"parentId\":1,\"memo\":null,\"menuName\":\"用户管理\",\"scode\":\"\",\"itype\":1,\"children\":[],\"menuSort\":3,\"iconCls\":\"\"}],\"parentId\":0}]}";
		return JSON.parseObject(result);
	}
	
}
