package com.yucong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.entity.User;
import com.yucong.service.PermissionService;
import com.yucong.service.UserService;


@RestController
public class IndexController {

    @Autowired
    private PermissionService resourceService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(/*@CurrentUser*/ User loginUser, Model model) {
       /* Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<Permission> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);*/
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
