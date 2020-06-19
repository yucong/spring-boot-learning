package com.java.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.java.model.MailBean;

/**
 * <P> 参考博客：https://blog.csdn.net/qq_31984879/article/details/87516363
 */
@Component
public class EmailService {
	
	private Logger logger = LoggerFactory.getLogger(EmailService.class);
	
    @Value("${spring.mail.username}")
    private String mailSender; //邮件发送者
 
    @Autowired
    private JavaMailSender javaMailSender;
 
    /**
     * <P>发送文本邮件
     *
     * @param mailBean
     */
    public  void sendSimpleMail(MailBean mailBean) {
        try {
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            mailMessage.setFrom(mailSender);
            mailMessage.setTo(mailBean.getRecipient(),"1253666706@qq.com");
            
            mailMessage.setSubject(mailBean.getSubject());
            mailMessage.setText(mailBean.getContent());
            
            // mailMessage.copyTo(null);
 
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }
    
    /**
     * <P> 发送HTML模板邮件
     * @param mailBean
     */
	public void sendHTMLMail(MailBean mailBean) {
	    MimeMessage mimeMailMessage = null;
	    try {
	        mimeMailMessage = javaMailSender.createMimeMessage();
	        //true 表示需要创建一个multipart message
	        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
	        mimeMessageHelper.setFrom(mailSender);
	        mimeMessageHelper.setTo(mailBean.getRecipient());
	        
	        
	        mimeMessageHelper.setSubject(mailBean.getSubject());
	        //邮件抄送
	        //mimeMessageHelper.addCc("抄送人");
	        mimeMessageHelper.setText(mailBean.getContent(), true);
	        javaMailSender.send(mimeMailMessage);
	    } catch (Exception e) {
	        logger.error("邮件发送失败", e.getMessage());
	    }
	}
	


}
