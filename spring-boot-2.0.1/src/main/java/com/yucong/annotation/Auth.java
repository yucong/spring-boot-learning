package com.yucong.annotation;

import java.lang.annotation.*;  

/** 
 * 在类或方法上添加@Auth就验证登录
 * 
 * @author 喻聪
 * @date   2018-01-25 
 *  
 */  
@Target({ElementType.TYPE, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface Auth {  
	
}
