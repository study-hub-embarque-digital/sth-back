package com.studyhub.sth.application.dtos.representante;
import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RepresentanteDto{
    private UUID id;
    private UsuarioDto usuarioDto;
    private EmpresaDto empresaDto;
    List<SquadDTO> squadDtos;
}