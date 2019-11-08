package com.yucong.dto.role;

import com.yucong.core.base.dto.PageInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ListRoleDTO extends PageInfo {
	
	private String roleName;
	private String keyWord;
	private Boolean available;
	
	
	
}
