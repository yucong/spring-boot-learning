package com.yucong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 喻聪
 * @date   2018-12-24
 */
@Controller
@RequestMapping
public class ThymeleafController {

    @GetMapping("/mv")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        // 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("thymeleaf");
        // 设置属性
        view.addObject("title", "thymeleaf模板渲染");
        view.addObject("desc", "欢迎使用spring-boot-thymeleaf");
        Author author = new Author();
        author.setAge(30);
        author.setEmail("2241268051@qq.com");
        author.setName("喻聪");
        view.addObject("author", author);
        return view;
    }

    @GetMapping("/map")
    public String index1(HttpServletRequest request) {
        // TODO 与上面的写法不同，但是结果一致。
        // 设置属性
        request.setAttribute("title", "thymeleaf模板渲染");
        request.setAttribute("desc", "欢迎使用spring-boot-thymeleaf");
        Author author = new Author();
        author.setAge(30);
        author.setEmail("2241268051@qq.com");
        author.setName("喻聪");
        request.setAttribute("author", author);
        // 返回的 index 默认映射到 src/main/resources/templates/xxxx.html
        return "thymeleaf";
    }

    class Author {
        private int age;
        private String name;
        private String email;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
