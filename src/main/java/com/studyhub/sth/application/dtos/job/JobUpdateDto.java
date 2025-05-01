package com.studyhub.sth.application.dtos.job;

import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.domain.entities.Empresa;
import com.studyhub.sth.domain.entities.Usuario;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobUpdateDto {
    private String cargo;
    private String areaAtuacao;
    private LocalDate dataTermino;
}
