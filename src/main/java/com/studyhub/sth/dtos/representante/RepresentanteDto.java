package com.studyhub.sth.dtos.representante;
import com.studyhub.sth.dtos.users.UsuarioDto;
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