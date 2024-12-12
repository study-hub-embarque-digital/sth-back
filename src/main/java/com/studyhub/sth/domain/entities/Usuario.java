package com.studyhub.sth.domain.entities;

import com.studyhub.sth.application.dtos.users.UsuarioUpdateDto;
import jakarta.persistence.*;
import lombok.*;

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
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID usuarioId;

    @Column(length = 350)
    private String nome;

    @Column(length = 350, unique = true)
    private String email;

    private String senha;
    private Date dataNascimento;

    @ManyToMany()
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public void atualizar(UsuarioUpdateDto usuarioAtualizadoDto) {
        this.nome = usuarioAtualizadoDto.getNome();
        this.email = usuarioAtualizadoDto.getEmail();
        this.senha = usuarioAtualizadoDto.getSenha();
        this.dataNascimento = usuarioAtualizadoDto.getDataNascimento();
    }
}