package com.max.vault.service.impl;

import com.max.vault.dto.request.EmailDetails;
import com.max.vault.service.EmailService;
import com.max.vault.utils.PropsReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender javaMailSender;
  private final PropsReader propsReader;

  @Override
  public void sendEmailAlert(EmailDetails emailDetails) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(propsReader.getSenderEmail());
    message.setTo(emailDetails.getRecipient());
    message.setText(emailDetails.getMessageBody());
    message.setSubject(emailDetails.getSubject());

    javaMailSender.send(message);
    log.info("Email sent successfully");
  }
}