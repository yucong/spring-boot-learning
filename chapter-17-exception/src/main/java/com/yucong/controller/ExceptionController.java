package com.yucong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.exception.CustomException;

/**
 * 全局异常演示
 *
 * @author 喻聪
 * @date   2018-12-28
 */
@RestController
public class ExceptionController {

    @GetMapping("test")
    public String test(Integer num) {
        // TODO 演示需要，实际上参数是否为空通过 @RequestParam(required = true)  就可以控制
        if (num == null) {
            throw new CustomException(400, "num不能为空");
        }
        int i = 10 / num;
        return "result:" + i;
    }

}
