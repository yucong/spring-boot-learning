package com.yucong.dto.role;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RoleIdDTO {

	@NotNull(message="roleId必填")
	public Long roleId;
}
