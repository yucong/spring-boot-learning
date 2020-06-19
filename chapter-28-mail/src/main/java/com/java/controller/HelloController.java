package com.java.controller;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 我的第一个SpringBoot程序
 * 其中 @RestController 等同于 （@Controller 与 @ResponseBody）
 *
 * @author 喻聪
 * @date   2018-12-24
 */
@RestController
public class HelloController {

	@GetMapping("/hi")
    public String hello() {
        return "Hello Spring Boot";
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        // 目的是:来看看 SpringBoot 默认为我们提供的 Bean
        return args -> {
            System.out.println("来看看 SpringBoot 默认为我们提供的 Bean：");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            Arrays.stream(beanNames).forEach(System.out::println);
        };
    }
}
