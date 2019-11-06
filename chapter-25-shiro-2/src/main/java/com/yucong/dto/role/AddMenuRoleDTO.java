package com.yucong.dto.role;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class AddMenuRoleDTO {

	@NotNull(message="enterpriseId必填")
	private Integer enterpriseId;
	@NotEmpty(message="roleName不能为空")
	private String roleName;
	private String roleDesc;
	private String roleCode;
	private List<String> menuIds;
}
