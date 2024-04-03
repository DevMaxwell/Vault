package com.max.vault.service.impl;

import com.max.vault.dto.request.EmailDetails;
import com.max.vault.service.EmailService;
import com.max.vault.utils.PropsReader;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
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

  @Override
  public void sendEmailWithAttachment(EmailDetails emailDetails) {
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper;
    try {
      mimeMessageHelper = new MimeMessageHelper(message, true);
      mimeMessageHelper.setFrom(propsReader.getSenderEmail());
      mimeMessageHelper.setTo(emailDetails.getRecipient());
      mimeMessageHelper.setText(emailDetails.getMessageBody());
      mimeMessageHelper.setSubject(emailDetails.getSubject());

      FileSystemResource fileSystemResource = new FileSystemResource(emailDetails.getAttachment());
      mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
      javaMailSender.send(message);
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }
}
