package com.max.vault.service;

import com.max.vault.dto.request.EmailDetails;

public interface EmailService {
  void sendEmailAlert(EmailDetails emailDetails);
}
