package com.studyhub.sth.application.dtos.alunos;

import com.studyhub.sth.application.dtos.users.UsuarioUpdateDto;
import com.studyhub.sth.domain.enums.Ciclo;
import com.studyhub.sth.domain.enums.Periodo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoUpdateDto {
    private UsuarioUpdateDto usuarioAtualizadoDto;
    private Periodo periodo;
    private String curso;
    private Ciclo ciclo;
    private boolean isWorkingInIt;
    private boolean isExemptedResidence;
}
