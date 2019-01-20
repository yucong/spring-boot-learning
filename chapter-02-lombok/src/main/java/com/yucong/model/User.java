package com.yucong.model;

import lombok.Data;

/**
 * 
 * 加上Data注解后，后自动写上get/set,toString等方法
 * 
 * @author 喻聪
 * @date   2018-12-24
 *
 */
@Data
public class User {

	private Integer id;
	private String name;
	
}
