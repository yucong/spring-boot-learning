package com.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.MailApplication;
import com.java.model.MailBean;
import com.java.service.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MailApplication.class)
public class MailApplicationTests {

	@Autowired
    private EmailService emailService;
 
    //@Autowired
    //private TemplateEngine templateEngine;
 
    //接收人
    private static final String RECIPINET = "2241268051@qq.com";
 
    /**
     * <P>发送文本邮件
     */
    @Test
    public void sendSimpleMail() {
        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMail之这是一封文本的邮件");
        mailBean.setContent("SpringBootMail发送一个简单格式的邮件，时间为：2020-06-18" );
        emailService.sendSimpleMail(mailBean);

    }
    
    /**
     * <p>发送html邮件
     */
    @Test
    public void sendHTMLMail() {
        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMailHTML之这是一封HTML格式的邮件");
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>SpirngBoot测试邮件HTML</h2>")
                .append("<p style='text-align:left'>这是一封HTML邮件...</p>")
                .append("<p> 时间为："+ "2020-06-28" +"</p>");
        mailBean.setContent(sb.toString());
        emailService.sendHTMLMail(mailBean);
    }


}