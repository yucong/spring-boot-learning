package com.yucong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.core.annotion.Auth;
import com.yucong.core.base.BaseVO;
import com.yucong.core.base.CommonVO;
import com.yucong.core.base.DataTableVO;
import com.yucong.entity.User;
import com.yucong.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Auth
@RestController
@RequestMapping("user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    
    @ApiOperation(value="用户列表")
    @GetMapping(value = "list")
    public CommonVO<DataTableVO<User>> list(Model model) {
    	return new CommonVO<DataTableVO<User>>(userService.findAll(1,10));
    }

    @ApiOperation(value="添加用户")
    @PostMapping(value = "add")
    public BaseVO add(@RequestBody User user) {
        userService.createUser(user);
        return BaseVO.success();
    }

    @ApiOperation(value="更新用户")
    @PostMapping(value = "update" )
    public BaseVO update(@RequestBody User user) {
        userService.updateUser(user);
        return BaseVO.success();
    }

    @ApiOperation(value="删除用户")
    @PostMapping(value = "delete")
    public BaseVO delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return BaseVO.success();
    }

    @ApiOperation(value="修改密码")
    @PostMapping(value = "changePassword")
    public BaseVO changePassword(@PathVariable("id") Long id, String newPassword) {
        userService.changePassword(id, newPassword);
        return BaseVO.success();
    }


}
