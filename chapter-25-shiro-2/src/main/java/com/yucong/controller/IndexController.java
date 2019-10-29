package com.yucong.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yucong.entity.Permission;
import com.yucong.entity.User;
import com.yucong.service.PermissionService;
import com.yucong.service.UserService;


@Controller
public class IndexController {

    @Autowired
    private PermissionService resourceService;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(/*@CurrentUser*/ User loginUser, Model model) {
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<Permission> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
