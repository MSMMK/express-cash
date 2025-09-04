package org.example.expresscash.model;

import lombok.Data;
import org.example.expresscash.constants.UserTypeEnum;

import java.util.Set;

@Data
public class UserModel {
    private Long id;
    private String username;
    private String email;
    private String profileImage;
    private LookupModel governorate;
    private LookupModel city;
    private UserTypeEnum userType;
    private BranchModel branch;
}
