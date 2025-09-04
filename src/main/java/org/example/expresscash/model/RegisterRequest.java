package org.example.expresscash.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.expresscash.constants.UserTypeEnum;
import org.example.expresscash.entity.RoleEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private Long superUserId;
    private String email;
    private String username;
    private String password;
    private String profileImage;
    private Long govId;
    private Long cityId;
    private UserTypeEnum userType;
    private String branchCode;
    private RoleEnum role;
}
