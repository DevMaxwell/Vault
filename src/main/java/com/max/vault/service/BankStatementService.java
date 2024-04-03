package com.max.vault.service;

import com.itextpdf.text.DocumentException;
import com.max.vault.model.Transaction;

import java.io.FileNotFoundException;
import java.util.List;

public interface BankStatementService {
  List<Transaction> generateStatement(String acctNum, String startDate, String endDate);

  void designStatementAndSendEMail(List<Transaction> transactions) throws FileNotFoundException, DocumentException;
}
