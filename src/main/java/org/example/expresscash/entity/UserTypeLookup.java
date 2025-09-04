package org.example.expresscash.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.expresscash.constants.UserTypeEnum;

@Data
@Entity
@Table(name = "user_type")
public class UserTypeLookup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private UserTypeEnum code;
    private String nameEn;
    private String nameAr;

}
