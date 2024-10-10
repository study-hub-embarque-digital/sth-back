package com.studyhub.sth.dtos.alunos;

import com.studyhub.sth.dtos.users.NovoUsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NovoAlunoDto {
    private NovoUsuarioDto novoUsuarioDto;
    private int periodo;
    private String curso;
}