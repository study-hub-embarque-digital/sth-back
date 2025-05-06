package com.studyhub.sth.domain.before.services;

import com.studyhub.sth.application.dtos.salasTematica.SalaTematicaDto;
import com.studyhub.sth.domain.before.enums.Dificuldade;

import java.util.List;
import java.util.UUID;

public interface ISalasTematicaService {
    List<SalaTematicaDto> obterPorRoom(UUID roomId, Dificuldade dificuldade);
}
