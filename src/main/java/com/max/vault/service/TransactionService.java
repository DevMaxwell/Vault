package com.max.vault.service;

import com.max.vault.dto.request.TransactionDto;

public interface TransactionService {
  void saveTransaction(TransactionDto transactionDto);
}
