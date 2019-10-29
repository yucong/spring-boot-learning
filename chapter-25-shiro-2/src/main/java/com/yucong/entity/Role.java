package com.yucong.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "sys_role")
@Data
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //编号
    private String role; //角色标识 程序中判断使用,如"admin"
    private String description; //角色描述,UI界面显示使用
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    public Role() {
    	
    }
    
    public Role(String role,String description,String resourceIds, Boolean available) {
    	this.role = role;
    	this.description = description;
    	this.available = available;
    }



}
