package com.yucong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.core.annotion.Auth;
import com.yucong.core.base.dto.PageInfo;
import com.yucong.core.base.vo.BaseVO;
import com.yucong.core.base.vo.CommonVO;
import com.yucong.core.base.vo.DataTableVO;
import com.yucong.core.util.BeanMapper;
import com.yucong.dto.user.AddUser;
import com.yucong.dto.user.UpdateUser;
import com.yucong.entity.User;
import com.yucong.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Auth
@RestController
@RequestMapping("user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="用户列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
    @GetMapping(value = "list")
    public CommonVO<DataTableVO<User>> list(PageInfo pageInfo) {
    	return new CommonVO<DataTableVO<User>>(userService.findAll(1,10));
    }

    @ApiOperation(value="添加用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
    @PostMapping(value = "add")
    public BaseVO add(@RequestBody @Valid AddUser user) {
    	User u = BeanMapper.map(user, User.class);
        userService.createUser(u);
        return BaseVO.success();
    }

    @ApiOperation(value="更新用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
    @PostMapping(value = "update" )
    public BaseVO update(@RequestBody @Valid UpdateUser user) {
    	User u = BeanMapper.map(user, User.class);
    	userService.updateUser(u);
        return BaseVO.success();
    }

    @ApiOperation(value="删除用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
    @PostMapping(value = "delete")
    public BaseVO delete(Long id) {
        userService.deleteUser(id);
        return BaseVO.success();
    }

    @ApiOperation(value="修改密码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户令牌", required = true, dataType = "string", paramType = "header"),
    })
    @PostMapping(value = "changePassword")
    public BaseVO changePassword(Long id, String newPassword) {
        userService.changePassword(id, newPassword);
        return BaseVO.success();
    }


}
