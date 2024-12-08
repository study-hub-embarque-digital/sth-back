package com.studyhub.sth.application.dtos.representante;

import com.studyhub.sth.application.dtos.users.UsuarioUpdateDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RepresentanteUpdateDto {
    private UsuarioUpdateDto usuarioDto;
    private UUID empresaId;
}
