package com.yucong.dto.role;

import javax.validation.constraints.NotNull;

import com.yucong.core.base.dto.PageInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ListRoleDTO extends PageInfo {

	@NotNull(message = "enterpriseId必填")
	private Integer enterpriseId;
	
	private String roleName;
	private String keyWord;
	
}
