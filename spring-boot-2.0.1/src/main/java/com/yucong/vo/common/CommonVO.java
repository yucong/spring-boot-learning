package com.yucong.vo.common;

import lombok.Data;

/**
 * 公共基础VO
 *  
 * @author 喻聪
 *
 */
@Data
public class CommonVO<T>  {

	private int code = 1;
	private String message = "操作成功";
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
		this.code = 0;
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
