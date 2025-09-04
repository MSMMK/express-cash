package org.example.expresscash.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String cif;
    private String serialNumber;
    private String password;
    private Long userId;
    private Long branchId;
}
