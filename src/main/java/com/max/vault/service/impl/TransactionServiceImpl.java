package com.max.vault.service.impl;

import com.max.vault.dto.request.TransactionDto;
import com.max.vault.model.Transaction;
import com.max.vault.repository.TransactionRepository;
import com.max.vault.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  @Override
  public void saveTransaction(TransactionDto transactionDto) {
    Transaction transaction = Transaction.builder()
        .transactionType(transactionDto.getTransactionType())
        .amount(transactionDto.getAmount())
        .transactionStatus(transactionDto.getTransactionStatus())
        .destAcct(transactionDto.getDestAcct())
        .sourceAcct(transactionDto.getSourceAcct())
        .build();

    transactionRepository.save(transaction);
  }
}
