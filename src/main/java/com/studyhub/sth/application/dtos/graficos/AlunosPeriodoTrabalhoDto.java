package com.studyhub.sth.application.dtos.graficos;

import com.studyhub.sth.domain.enums.Periodo;

public interface AlunosPeriodoTrabalhoDto {
    Periodo getPeriodo();
    Long getTotalAlunos();
    Long getTotalTrabalhando();
}