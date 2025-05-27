package com.studyhub.sth.application.dtos.graficos;

import com.studyhub.sth.domain.enums.Ciclo;

public interface EmpresaPorCicloDTO {
    Ciclo getCiclo();
    Long getTotalEmpresas();
}