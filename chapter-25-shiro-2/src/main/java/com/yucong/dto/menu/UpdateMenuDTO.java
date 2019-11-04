package com.yucong.dto.menu;

import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class UpdateMenuDTO {
	
	@NotNull(message="id必填")
	private Integer id;
	@NotNull(message="parentId必填")
	public Integer parentId;
	//@NotBlank
	private String menuName;
	//@NotBlank
	private String menuPath;
	//@NotBlank
	private String scode;
	//@NotNull
	private Integer menuSort;
	private String iconCls;
	private String description;
}
