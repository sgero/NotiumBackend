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

    @Column(name = "token_verificacion")
    private String tokenVerificacion;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "verificado")
    private Boolean verificado;

    @OneToMany(mappedBy = "user")
    private Set<OcioNocturno> ocioNocturnoSet;

    @OneToMany(mappedBy = "user")
    private Set<Restaurante> restauranteSet;

//    @Column(name = "verificado")
//    private Boolean verificado;


    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToOne(mappedBy = "user")
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

    public void setVerificado(boolean b) {
    }

    public Object orElse(Object o) {
        return null;
    }
}
