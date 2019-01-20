package com.yucong.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.dto.AddUserDTO;
import com.yucong.dto.UpdateUserDTO;
import com.yucong.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 用户控制层 简单演示增删改查及分页
 * 新增了swagger文档内容 2018-07-21
 * @author oKong
 *
 */
@RestController
@RequestMapping("/user")
@Api(tags="用户API")
public class UserController {

	
	@PostMapping("add")
	@ApiOperation(value="用户新增")
	public Map<String,String> addUser(@Valid @RequestBody AddUserDTO dto){
		Map<String,String> result = new HashMap<String,String>();
		result.put("respCode", "01");
		result.put("respMsg", "新增成功");
		return result;
	}
	
	@PostMapping("update")
	@ApiOperation(value="用户修改")	
	public Map<String,String> updateUser(@Valid @RequestBody UpdateUserDTO dto){
		Map<String,String> result = new HashMap<String,String>();
		result.put("respCode", "01");
		result.put("respMsg", "更新成功");
		return result;
	}
	
	@GetMapping("/get/{id}")
	@ApiOperation(value="用户查询(ID)")	
	@ApiImplicitParam(name="id",value="查询ID",required=true)
	public Map<String,Object> getUser(@PathVariable("id") Long id){
		//查询
		User user = new User();
		user.setId(id);
		user.setUsername("张三");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", "01");
		result.put("respMsg", "成功");
		result.put("data", user);
		return result;

	}
	
	@GetMapping("/page")
	@ApiOperation(value="用户查询(分页)")		
	public Map<String,Object> pageUser(int current, int size){
		//分页
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", "01");
		result.put("respMsg", "成功");
		//result.put("data", userService.selectPage(page));
		return result;
	}
		
}


