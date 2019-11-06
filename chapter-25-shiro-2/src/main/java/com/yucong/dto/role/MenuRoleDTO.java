package com.yucong.dto.role;

import java.util.List;

import lombok.Data;

@Data
public class MenuRoleDTO {

	private Integer roleId;
	private String roleName;
	private String roleCode;
	private String roleDesc;
	private List<String> menuIds;
}
