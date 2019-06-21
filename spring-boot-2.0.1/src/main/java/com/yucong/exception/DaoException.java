package com.yucong.exception;

public class DaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private int errorCode;
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public DaoException(int errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
	}
	
}
