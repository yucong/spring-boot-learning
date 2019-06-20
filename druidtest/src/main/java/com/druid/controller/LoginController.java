package com.druid.controller;

import com.druid.entity.LoginUser;
import com.druid.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName druidtest
 * @Auther:GuoFeng
 * @Date: 2019/6/20.
 * @Desoription TODO
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginUserService loginUserService;

    @GetMapping("/get")
    private LoginUser getUser(){
        return loginUserService.getLoginUser(1);
    }
}
