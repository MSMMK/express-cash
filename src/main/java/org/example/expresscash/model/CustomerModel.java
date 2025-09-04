package org.example.expresscash.model;

import lombok.Data;
import org.example.expresscash.constants.StatusEnum;

import java.util.Set;

@Data
public class CustomerModel {
    private Long id;
    private String name;
    private String cif;
    private String email;
    private StatusEnum status;
    private Set<String> simNumbers;
    private BranchModel branch;
}
