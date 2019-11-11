package com.yucong.vo.user;

import java.util.List;

import com.yucong.entity.Role;

import lombok.Data;

@Data
public class ListUserVO {

	private Long id;
	private Long organizationId; //所属公司
    private String username; //用户名
    private Boolean locked;
	
    private List<Role> roles;
    
}
