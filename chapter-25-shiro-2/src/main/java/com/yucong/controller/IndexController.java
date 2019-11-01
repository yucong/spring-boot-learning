package com.yucong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.core.annotion.CurrentUser;


@RestController
public class IndexController {

   /* @Autowired
    private PermissionService resourceService;
    @Autowired
    private UserService userService;*/

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


}
