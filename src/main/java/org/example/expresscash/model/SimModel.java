package org.example.expresscash.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.expresscash.constants.StatusEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimModel {
    private Long id;
    private String phoneNumber;
    private BranchModel branch;
    private Double totalBalance;
    private Double dailyBalance;
    private Double monthlyBalance;
    private StatusEnum status;
    private CustomerModel customer;
}
