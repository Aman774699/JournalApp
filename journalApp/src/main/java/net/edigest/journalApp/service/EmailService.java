package net.edigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendMail(String to,String subject,String body)
    {
   try{
       SimpleMailMessage mail=new SimpleMailMessage();
       mail.setFrom("vishwakarmaaman@gmail.com");
       mail.setTo(to);
       mail.setSubject(subject);
       mail.setText(body);
       javaMailSender.send(mail);
       log.info("Mail Send successfully to{}" ,to);
   }
       catch (Exception e)
       {
           log.error("Error occur while sending the mail to{}", to,e.getMessage());
           log.error("Error",e);
       }
    }
}
