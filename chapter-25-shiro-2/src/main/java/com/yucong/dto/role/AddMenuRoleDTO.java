package com.yucong.dto.role;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Data
public class AddMenuRoleDTO {

	@NotEmpty(message="roleName不能为空")
	private String roleName;
	private String roleDesc;
	private String roleCode;
	private List<String> menuIds;
}
