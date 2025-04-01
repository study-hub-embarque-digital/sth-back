package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.salasTematica.SalaTematicaDto;

import java.util.List;
import java.util.UUID;

public interface ISalasTematicaService {
    List<SalaTematicaDto> obterPorRoom(UUID roomId);
}
