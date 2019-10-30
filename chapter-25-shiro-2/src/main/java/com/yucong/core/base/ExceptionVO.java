package com.yucong.core.base;

import lombok.Data;

@Data
public class ExceptionVO  {

	private int code;
	private String message;
	private int errorCode;
	private String errorMsg;

}
