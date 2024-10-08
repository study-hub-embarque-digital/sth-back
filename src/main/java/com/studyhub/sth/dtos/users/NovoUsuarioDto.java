package com.studyhub.sth.dtos.users;

import jakarta.persistence.Column;

import java.util.Date;

public record NovoUsuarioDto(String nome, String email, String senha, Date dataNascimento) {
}