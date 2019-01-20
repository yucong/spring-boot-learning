package com.yucong.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.pojo.Book;

import javax.validation.constraints.NotBlank;

/**
 * 参数校验
 *
 * @author 喻聪
 * @date   2018-12-28
 */
@Validated
@RestController
public class ValidateController {


    @GetMapping("/test1")
    public String test1(@NotBlank(message = "name 不能为空") @Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间") String name) {
        return "success";
    }

    @GetMapping("/test2")
    public String test2(@Validated Book book) {
        return "success";
    }
    


}
