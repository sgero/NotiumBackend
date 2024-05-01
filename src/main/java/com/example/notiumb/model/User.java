package com.example.notiumb.model;


import com.example.notiumb.model.enums.Rol;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usuario", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"ocioNocturnoSet", "restauranteSet", "cliente", "rpp"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rol", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Rol rol;

    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<OcioNocturno> ocioNocturnoSet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Restaurante> restauranteSet;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Rpp rpp;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
