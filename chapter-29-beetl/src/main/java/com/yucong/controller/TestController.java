package com.yucong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 参考博客：https://blog.csdn.net/userlhj/article/details/89916784
 *
 * @author 喻聪
 * @date   2020-07-12
 */
@Controller
public class TestController {

	@RequestMapping("/test")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username","jackson");
        modelAndView.setViewName("beetl/test");
        return modelAndView;
    }

}
