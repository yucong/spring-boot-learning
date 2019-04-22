package com.yucong.controller;

import org.mindrot.jbcrypt.BCrypt;

/**
 * 参考博客：https://www.cnblogs.com/xingzc/p/8624007.html
 * 
 * 项目中用到需要对登录密码进行加密 ，使用BCrypt 主要是能实现每次加密的值都是不一样的。
 */
public class Test {

	public static void main(String[] args) {
		
		// $2a$10$ofPkBDUezOJp6Sik63Q/0.QlU8a1itEyzldjSXqfn2nDPqXjN0Ljm
		// $2a$10$ootQb4WmMFaZT8m5xwRqBe3xPeED8aO1lWyuuWi7EiT8sOX9Os4c.
		String password = "123456a";
		
		//加密 【注：每次加密后的值都是不一样的】
		String pwt = BCrypt.hashpw(password, BCrypt.gensalt());

		//解密
		boolean pswFlag = BCrypt.checkpw(password,"$2a$10$ootQb4WmMFaZT8m5xwRqBe3xPeED8aO1lWyuuWi7EiT8sOX9Os4c.");

		System.out.println(pwt+"===="+pswFlag);
	}
	
}
