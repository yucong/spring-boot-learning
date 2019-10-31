package com.yucong.core.base.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公共基础VO
 *  
 * @author 喻聪
 * @date   2019-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommonVO<T> extends BaseVO  {

	private T data;
	
	public T getData() {
		return data;
	}
	
	public CommonVO() {
		super();
	}

	public CommonVO(T data) {
		super();
		this.data = data;
	}
	
	public CommonVO(String errMsg) {
		super.code = 0;
		this.message = errMsg;
	}
	
	public CommonVO(int code,String message) {
		this.code = code;
		this.message = message;
	}
	
	public CommonVO(String message,T data) {
		this.message = message;
		this.data = data;
	}
	
	public CommonVO(int code,String message,T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	
}
