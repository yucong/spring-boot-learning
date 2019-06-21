package com.yucong.enums;

public enum CodeEnum {

	/**业务处理成功*/
	SUCCEED(1,"操作成功"),
	/**客户端异常：请求参数不合法,请求方式不合法等等*/
	CLIENT_ERROR(0,"操作失败"),
	/**业务处理失败：-1：验证码错误，已点赞，已关注，已取消关注，查看帖子详情不存在，*/
	BUSINESS_ERROR(-1,"业务处理失败"),
	/**token验证失败*/
	AUTH_FAIL(-2,"token验证失败"),
	/**签名认证失败*/
	SIGN_FAIL(-3,"签名认证失败"),
	/**服务器内部错误*/
	SERVER_ERROR(-99,"服务器内部错误");
	
	private int code;
	private String name;
	
	CodeEnum(int code,String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
