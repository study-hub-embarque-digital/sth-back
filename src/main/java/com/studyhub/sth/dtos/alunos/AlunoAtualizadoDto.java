package com.studyhub.sth.dtos.alunos;

import com.studyhub.sth.dtos.users.UsuarioAtualizadoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoAtualizadoDto {
    private UsuarioAtualizadoDto usuarioAtualizadoDto;
    private int periodo;
    private String curso;
}
