package com.yucong.exception;

/**
 * 参数非法异常
 * 
 * @author 喻聪
 * @date   2017-08-02
 *
 */
public class ParameterIllegalException extends RuntimeException {
 
	private static final long serialVersionUID = 2236670700772835546L;
	
	private int errorCode = 400;//请求参数校验未通过
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public ParameterIllegalException(String errorMsg) {
		super(errorMsg);
	}
	
	public ParameterIllegalException(int errorCode,String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
	}

	
	
	
	
	
	
	

}
