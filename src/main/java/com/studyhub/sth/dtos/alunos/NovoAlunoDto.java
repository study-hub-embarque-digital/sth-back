package com.studyhub.sth.dtos.alunos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NovoAlunoDto {
    private int periodo;
    private String curso;
}
