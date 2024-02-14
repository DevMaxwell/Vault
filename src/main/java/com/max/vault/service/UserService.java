package com.max.vault.service;

import com.max.vault.dto.request.EnquiryRequest;
import com.max.vault.dto.request.UserRequest;
import com.max.vault.dto.response.BankResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  BankResponse createUser(UserRequest userRequest);

  BankResponse balancenquiry(EnquiryRequest enquiryRequest);
  BankResponse nameEnquiry(EnquiryRequest enquiryRequest);
}
