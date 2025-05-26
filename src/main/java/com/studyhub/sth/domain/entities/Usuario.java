package com.studyhub.sth.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.studyhub.sth.application.dtos.users.UsuarioUpdateDto;
import com.studyhub.sth.domain.enums.Ethnicity;
import com.studyhub.sth.domain.enums.Gender;
import com.studyhub.sth.domain.exceptions.PhoneNotValidException;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "usuarios")
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "usuarioId")
public class Usuario implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID usuarioId;

    @Column(length = 350)
    private String nome;

    @Column(length = 350, unique = true)
    private String email;

    private String senha;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dataNascimento;

    @Column(name = "is_active", nullable = true)
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Enumerated(EnumType.STRING)
    private Ethnicity ethnicity ;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "usuario")
    private List<Job> jobs;

    private String fotoPerfil;

    @Column(name = "has_job")
    private Boolean hasJob;

    public Usuario(UUID usuarioId, String nome, String email, String senha, Date dataNascimento, List<Role> roles, Ethnicity ethnicity, String phone, Gender gender, Boolean isActive) {
        this.usuarioId = usuarioId;
        this.isActive = isActive;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.roles = roles;
        this.ethnicity = ethnicity;
        this.phone = phone;
        this.gender = gender;
    }

    public void setPhone(String phone) throws PhoneNotValidException {
        if(!phone.matches("\\d{2}\\d{4,5}\\d{4}")) {
            throw new PhoneNotValidException("Telefone inválido! Verifique e tente novamente!");
        }

        this.phone = phone;
    }

    public void atualizar(UsuarioUpdateDto usuarioAtualizadoDto) {
        this.nome = usuarioAtualizadoDto.getNome();
        this.email = usuarioAtualizadoDto.getEmail();
        this.senha = usuarioAtualizadoDto.getSenha();
        this.isActive = usuarioAtualizadoDto.getIsActive();
        this.phone = usuarioAtualizadoDto.getPhone();
        this.gender = usuarioAtualizadoDto.getGender();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNome())).toList();
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.email;
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