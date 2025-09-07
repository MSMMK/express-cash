package org.example.expresscash.services;

import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.model.TransactionHistoryModel;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionHistoryService {
    void createTransaction(TransactionHistoryModel transactionHistoryModel);
    List<TransactionHistoryModel> listTransactions(SearchCriteria searchCriteria, Pageable pageable);
    void deleteTransaction(Long transactionId);

    ResponseEntity<byte[]> export(SearchCriteria searchCriteria, Pageable pageable);
}
