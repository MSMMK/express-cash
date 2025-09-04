package org.example.expresscash.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sim_details")
public class Sim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Ensure this matches the column in the database
    private Long id;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = false)
    private Branch branch;

    private String phoneNumber;
    private Double totalBalance;
    private Double dailyBalance;
    private Double monthlyBalance;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
}
