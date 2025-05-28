package com.studyhub.sth.application.dtos.graficos;

import com.studyhub.sth.domain.enums.Cursos;

public interface CursoCountDto {
    Cursos getCurso();
    Long getQuantidade();
}

