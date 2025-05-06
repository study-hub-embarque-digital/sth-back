package com.studyhub.sth.application.dtos.salasTematica;


import com.studyhub.sth.domain.before.entities.SalaTematica;

import java.util.UUID;

public record SalaTematicaDto(UUID salaTematicaId, String nome) {
    public SalaTematicaDto(SalaTematica salaTematica) {
        this(salaTematica.getSalaTematicaId(), salaTematica.getTopico().getTitulo());
    }
}
