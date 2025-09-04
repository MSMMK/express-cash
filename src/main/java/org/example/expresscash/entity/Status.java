package org.example.expresscash.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.expresscash.constants.StatusEnum;

@Data
@Entity
@Table(name = "status")
@NoArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "CODE")
    @Enumerated(EnumType.STRING)
    private StatusEnum code;
    @Column(name = "NAME_EN")
    private String nameEn;
    @Column(name = "NAME_AR")
    private String nameAr;

    public Status(Long id) {
        this.id = id;
    }
}
