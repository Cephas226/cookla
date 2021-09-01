package com.oms.service;

import com.oms.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
 	private JavaMailSender javaMailSender;

  @Autowired
  public MailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }
  public void sendEmail(Employee employee) throws MailException {

    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setTo(employee.getEmail());
    mail.setSubject("Test OMS password recovey");
    mail.setText("Hello this is a test");
    javaMailSender.send(mail);
  }
}
