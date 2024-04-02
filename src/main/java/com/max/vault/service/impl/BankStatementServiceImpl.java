package com.max.vault.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.max.vault.model.Transaction;
import com.max.vault.repository.TransactionRepository;
import com.max.vault.service.BankStatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankStatementServiceImpl implements BankStatementService {

  private final TransactionRepository transactionRepository;

  private static final String FILE = "/Users/maxwell/Documents/vaultPDF";
  @Override
  public List<Transaction> generateStatement(String acctNum, String startDate, String endDate) {

    LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
    LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

    return transactionRepository.findAll().stream()
        .filter(
        transaction -> {
          return transaction.getDestAcct().equals(acctNum) || transaction.getSourceAcct().equals(acctNum);
        }).filter(transaction -> transaction.getCreatedAt().isAfter(start.atStartOfDay()))
        .filter(transaction -> transaction.getCreatedAt().isBefore(end.atStartOfDay())).toList();
  }

  @Override
  public void designStatement(List<Transaction> transactions)
      throws FileNotFoundException, DocumentException {
    Rectangle statementSize = new Rectangle(PageSize.A4);
    Document document = new Document(statementSize);
    OutputStream outputStream = new FileOutputStream(FILE);
    PdfWriter.getInstance( document, outputStream);

    PdfPTable bankInfo = new PdfPTable(1);
    PdfPCell bankName = new PdfPCell(new Phrase("Vault. Bank but for the young at heart"));
    bankName.setBorder(0);
    bankName.setBackgroundColor(BaseColor.BLACK);
    bankName.setPadding(20f);
    PdfPCell bankAddr = new PdfPCell(new Phrase(""));
    bankAddr.setBorder(0);
    bankInfo.addCell(bankName);
    bankInfo.addCell(bankAddr);

    PdfPTable statementInfo = new PdfPTable(2);
    PdfPCell customerInfo = new PdfPCell(new Phrase("Start Date "));
    customerInfo.setBorder(0);
    PdfPCell sttmAcct = new PdfPCell(new Phrase("Statement Of Account"));
    sttmAcct.setBorder(0);
    PdfPCell endDate = new PdfPCell(new Phrase("End Date "));
    endDate.setBorder(0);
    PdfPCell custName = new PdfPCell(new Phrase("Customer Name: "));
    custName.setBorder(0);
    PdfPCell space = new PdfPCell();
    space.setBorder(0);
    PdfPCell adddress = new PdfPCell(new Phrase("Address : "));
    adddress.setBorder(0);


    PdfPTable statementRecord = new PdfPTable(4);
    PdfPCell date = new PdfPCell(new Phrase("Date"));
    date.setBackgroundColor(BaseColor.BLUE);
    date.setBorder(0);
    PdfPCell rType = new PdfPCell(new Phrase("Transaction Type"));
    rType.setBackgroundColor(BaseColor.BLUE);
    rType.setBorder(0);
    PdfPCell amount = new PdfPCell(new Phrase("Amount"));
    amount.setBackgroundColor(BaseColor.BLUE);
    amount.setBorder(0);
    PdfPCell rStatus = new PdfPCell(new Phrase("Status"));
    rStatus.setBackgroundColor(BaseColor.BLUE);
    rStatus.setBorder(0);

    statementRecord.addCell(date);
    statementRecord.addCell(rType);
    statementRecord.addCell(amount);
    statementRecord.addCell(rStatus);

    transactions.forEach(transaction -> {
      statementRecord.addCell(new Phrase(String.valueOf(transaction.getCreatedAt())));
      statementRecord.addCell(new Phrase(transaction.getTransactionType()));
      statementRecord.addCell(new Phrase(transaction.getAmount()));
      statementRecord.addCell(new Phrase(transaction.getTransactionStatus()));
    });

    statementInfo.addCell(customerInfo);
    statementInfo.addCell(sttmAcct);
    statementInfo.addCell(endDate);
    statementInfo.addCell(custName);
    statementInfo.addCell(space);
    statementInfo.addCell(adddress);


    document.add(bankInfo);
    document.add(statementInfo);
    document.add(statementRecord);

    document.close();

  }
}
