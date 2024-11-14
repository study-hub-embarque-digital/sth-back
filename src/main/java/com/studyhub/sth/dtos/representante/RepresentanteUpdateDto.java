package com.studyhub.sth.dtos.representante;

import com.studyhub.sth.dtos.users.UsuarioUpdateDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RepresentanteUpdateDto {
    private UsuarioUpdateDto usuarioDto;
    private UUID empresaId;
}
