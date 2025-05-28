package com.studyhub.sth.application.dtos.graficos;

import com.studyhub.sth.domain.enums.Gender;

public interface AlunosPorGeneroDto {
    Gender getGenero();
    Long getTotal();
}
