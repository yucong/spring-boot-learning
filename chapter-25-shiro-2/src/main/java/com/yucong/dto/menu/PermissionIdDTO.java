package com.yucong.dto.menu;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PermissionIdDTO {

	@NotNull(message="id必填")
	private Integer id;
}
