package com.studyhub.sth.dtos.representante;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.List;

@Getter
@Setter
public class NovoRepresentanteDto {
    private UUID usuarioId;
    private UUID empresaId;
    private List<UUID> squads;
}
