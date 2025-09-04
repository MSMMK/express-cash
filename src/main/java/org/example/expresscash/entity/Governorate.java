package org.example.expresscash.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "governorate_lookup")
@Entity
@NoArgsConstructor
public class Governorate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME_EN")
    private String nameEn;
    @Column(name = "NAME_AR")
    private String nameAr;

    public Governorate(Long id) {
        this.id = id;
    }
}
