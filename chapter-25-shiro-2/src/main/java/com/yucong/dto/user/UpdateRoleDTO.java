package com.yucong.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class UpdateRoleDTO {

	private Long userId;
	private List<Long> roleIds;
	
}
