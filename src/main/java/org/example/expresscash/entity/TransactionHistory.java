package org.example.expresscash.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction_history")
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "sim_id", referencedColumnName = "id", nullable = false)
    private Sim sim;

    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;

    @OneToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = false)
    private Branch branch;

    @OneToOne
    @JoinColumn(name = "transaction_type_id", referencedColumnName = "id", nullable = false)
    private TransactionTypeLookup transactionType;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @Column(name = "transaction_amount")
    private BigDecimal amount;

    private BigDecimal discount;
    private LocalDateTime creationDate;
    private LocalDateTime lastModificationDate;
    private String notes;
    private int oneEgp;
    private int fiveEgp;
    private int tenEgp;
    private int twentyEgp;
    private int fiftyEgp;
    private int oneHundredEgp;
    private int twoHundredEgp;
}
