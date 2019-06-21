package com.yucong.annotation;

import java.lang.annotation.*;  

/** 
 * 在方法上添加@Ignore就 不 验证登录
 * 
 * @author 喻聪
 * @date   2018-01-25 
 *  
 */  
@Target({ElementType.TYPE, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface Ignore {  
	
}
