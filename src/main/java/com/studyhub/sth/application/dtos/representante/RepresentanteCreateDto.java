package com.studyhub.sth.application.dtos.representante;

import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RepresentanteCreateDto {
    @Valid
    private UsuarioCreateDto novoUsuarioDto;
    private UUID empresaId;
}
