package org.example.expresscash.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "branch")
@NoArgsConstructor
public class Branch {
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
    @Column(name = "user_id")
    private Long userId;
    @OneToOne
    @JoinColumn(name = "governorate_id", referencedColumnName = "id", nullable = false)
    private Governorate governorate;
    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private City city;

    public Branch(Long id) {
        this.id = id;
    }
}
