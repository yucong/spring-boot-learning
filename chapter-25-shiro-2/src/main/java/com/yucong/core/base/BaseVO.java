package com.yucong.core.base;

import lombok.Data;

/**
 * 公共基础VO
 *  
 * @author 喻聪
 * @date   2019-05-01
 */
@Data
public class BaseVO  {

	protected int code = 1;
	protected String message = "操作成功";
	
	public BaseVO() {
		super();
	}
	
	public BaseVO(String errMsg) {
		this.code = 0;
		this.message = errMsg;
	}
	
	public BaseVO(int code,String message) {
		this.code = code;
		this.message = message;
	}
	
}
