package com.yucong.vo.role;

import java.util.Date;

import lombok.Data;

@Data
public class RoleVO {

	private Long id;
	private String role;
	private String description;
	private Date createTime;
}
