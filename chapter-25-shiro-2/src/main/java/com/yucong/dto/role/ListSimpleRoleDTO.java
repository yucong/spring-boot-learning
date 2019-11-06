package com.yucong.dto.role;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ListSimpleRoleDTO  {

	@NotNull(message = "enterpriseId必填")
	private Integer enterpriseId;
	
}
