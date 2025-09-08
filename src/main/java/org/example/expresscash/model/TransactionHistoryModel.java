package org.example.expresscash.model;

import lombok.Data;
import org.example.expresscash.constants.TransactionTypeEnum;

import java.time.LocalDateTime;

@Data
public class TransactionHistoryModel {
    private Long id;
    private SimModel sim;
    private UserModel user;
    private String customerPhoneNumber;
    private BranchModel branch;
    private CustomerModel customer;
    private TransactionTypeEnum transactionType;
    private Double amount;
    private Double discount;
    private int oneEgp;
    private int fiveEgp;
    private int tenEgp;
    private int twentyEgp;
    private int fiftyEgp;
    private int oneHundredEgp;
    private int twoHundredEgp;
    private String transactionId;
    private String notes;
    private LocalDateTime creationDate;
}
