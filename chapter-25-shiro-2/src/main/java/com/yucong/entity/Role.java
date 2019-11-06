package com.yucong.entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "sys_role")
public class Role extends BaseEntity {
	
    private String role; //角色标识 程序中判断使用,如"admin"
    private String description; //角色描述,UI界面显示使用
    private Boolean available; //是否可用,如果不可用将不会添加给用户

    public Role() {
    	
    }
    
    public Role(String role,String description,String resourceIds, Boolean available) {
    	this.role = role;
    	this.description = description;
    	this.available = available;
    }



}
