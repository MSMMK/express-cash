package org.example.expresscash.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class BranchModel {
    private Long id;
    private String code;
    @NotNull(message = "ENGLISH NAME REQUIRED")
    private String nameEn;
    @NotNull(message = "ARABIC NAME REQUIRED")
    private String nameAr;
    private LookupModel governorate;
    private LookupModel city;
}
