package com.yucong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestConfigFilesController {

	/*@RequestMapping
	public String test(){
		int a = 1000;
		int b = 300;
		int c = a + b;
		System.out.println("test spring loader,c is " + c);
		return "index";
	}*/
	
	@RequestMapping(value="test")
	public String test2(){
		int a = 200;
		int b = 300;
		int c = a + b;
		System.out.println("test spring loader,c is " + c);
		return "index";
	}
	
}
