package com.finsurge.Task60ChatGpt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("< sender mail id >");
        mailSender.setPassword("< password >");
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(props);

        return mailSender;
    }
}
