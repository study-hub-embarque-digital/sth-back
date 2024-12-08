package com.studyhub.sth.application.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateDto {
    private String nome;
    private String email;
    private String senha;
    private Date dataNascimento;
}