package org.example.expresscash.model;

import lombok.Data;

@Data
public class LookupModel {
    private Long id;
    private String code;
    private String nameEn;
    private String nameAr;
    private String govId;
}
