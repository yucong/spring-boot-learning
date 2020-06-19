package com.java.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class MailBean implements Serializable {
    
	private static final long serialVersionUID = -2116367492649751914L;
    private String recipient;//邮件接收人
    private String subject; //邮件主题
    private String content; //邮件内容

}
