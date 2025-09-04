package org.example.expresscash.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String email;
    private String profileImage;


    @OneToOne
    @JoinColumn(name = "user_type_id", referencedColumnName = "id", nullable = false)
    private UserTypeLookup userType;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;
    @OneToOne
    @JoinColumn(name = "governorate_id", referencedColumnName = "id", nullable = false)
    private Governorate governorate;
    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private City city;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @ToString.Exclude
    private User superUser;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = false)
    private Branch branch;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public User(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

}


