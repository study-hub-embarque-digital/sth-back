package com.studyhub.sth.dtos.representante;

import com.studyhub.sth.dtos.users.NovoUsuarioDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.List;

@Getter
@Setter
public class NovoRepresentanteDto {
    private NovoUsuarioDto novoUsuarioDto;
    private UUID empresaId;
}
