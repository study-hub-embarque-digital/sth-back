package com.studyhub.sth.dtos.alunos;

import com.studyhub.sth.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDto {
    private int periodo;
    private String curso;
    private Usuario usuario;
}