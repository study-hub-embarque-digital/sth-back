package com.studyhub.sth.dtos.representante;

import com.studyhub.sth.dtos.users.UsuarioCreateDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RepresentanteCreateDto {
    private UsuarioCreateDto novoUsuarioDto;
    private UUID empresaId;
}
