package com.yucong.core.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author 喻聪
 */
@Data
@ApiModel(description="统一分页查询参数对象")
public class PageInfo {

	@ApiModelProperty(value = "分页查询的页数，默认查询第一页")
	private int page = 1;  //默认为第1页
	
	@ApiModelProperty(value = "分页查询每页的条数，默认为20")
	private int size = 20; //默认每页大小为20
}
