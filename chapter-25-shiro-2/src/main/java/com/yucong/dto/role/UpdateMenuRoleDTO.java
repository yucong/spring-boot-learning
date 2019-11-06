package com.yucong.dto.role;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateMenuRoleDTO {
	
	@NotNull(message="roleId必填")
	private Integer roleId;
	
	@NotNull(message="enterpriseId必填")
	private Integer enterpriseId;
	
	@NotEmpty(message="roleName不能为空")
	private String roleName;
	
	private String roleCode;
	private String roleDesc;
	private List<String> menuIds;
}
