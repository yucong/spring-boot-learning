package com.yucong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestConfigFilesController {

	@RequestMapping(value="test21")
	public String test(){
		int a = 108;
		int b = 500;
		int c = a + b;
		System.out.println("c:" + c);
		return "index";
	}
	
}
