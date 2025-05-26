package com.studyhub.sth.domain.services;

import java.util.List;
import java.util.UUID;
import com.studyhub.sth.application.dtos.duvida.*;

public interface IDuvidaService {
    List<DuvidaDto> findAll();
    DuvidaSolucoesDto findByIdWithSolucao(UUID id);
    DuvidaDto create(NewDuvidaDto newDuvidaDto);
    DuvidaDto update(UUID duvidaId, UpdateDuvidaDto updateDuvidaDto);
    void delete(UUID id);
    void marcarComoResolvida(UUID id);
}
