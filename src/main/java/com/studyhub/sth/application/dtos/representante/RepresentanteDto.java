package com.studyhub.sth.application.dtos.representante;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RepresentanteDto{
    private UUID id;
    private UsuarioDto usuarioDto;
    private UUID empresaId;
}