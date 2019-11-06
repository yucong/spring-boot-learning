package com.yucong.vo.role;

import java.util.Date;

import lombok.Data;

@Data
public class SysRoleVO {

	private Integer id;
	private String roleName;
	private String roleDesc;
	private Date createTime;
}
