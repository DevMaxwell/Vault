package com.max.vault.service;

import com.max.vault.dto.request.CrDrRequest;
import com.max.vault.dto.request.EnquiryRequestByAccNum;
import com.max.vault.dto.request.EnquiryRequestByEmail;
import com.max.vault.dto.request.UserRequest;
import com.max.vault.dto.response.BankResponse;

public interface UserService {
  BankResponse createUser(UserRequest userRequest);

  BankResponse balancenquiry(EnquiryRequestByAccNum enquiryRequest);
  String nameEnquiry(EnquiryRequestByEmail enquiryRequest);

  BankResponse fundstransfer(CrDrRequest crDrRequest);
  BankResponse debitAccount(CrDrRequest crDrRequest);

//  BankResponse creditAccount(EnquiryRequest enquiryRequest);
//  BankResponse multipleFundsTransfer(EnquiryRequest enquiryRequest);
}
