package org.example.expresscash.model;

import lombok.Data;
import org.example.expresscash.constants.TransactionTypeEnum;

@Data
public class SearchCriteria {
    private String query;
    private Long userId;
    private Long branchId;
    private boolean isAdmin;
    private Long simId;
    private TransactionTypeEnum transactionType;
}
