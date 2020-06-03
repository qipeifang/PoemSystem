package com.springboot.service;

import com.springboot.utils.RandomCheckNumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    //从properties读取发送者的名字作为邮件的发件人
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;
    private RandomCheckNumberUtil checkNumber = new RandomCheckNumberUtil();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public String sendSimpleMail(String to){
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(from);
        //发送给谁
        message.setTo(to);
        //设置主题
        message.setSubject("changePassword");
        //设置内容
        String check = checkNumber.getNonce_str();

        message.setText("验证码"+check + "\n" + "修改密码"+"http://localhost:3000/setNewPassword");
        try {
            mailSender.send(message);
            logger.info("邮件已发送。");
        } catch (Exception e) {
            logger.error("发送邮件时发生异常了！",e);
        }
        return check;
    }


}
