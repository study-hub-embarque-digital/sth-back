package com.studyhub.sth.domain.services;

import java.util.List;
import java.util.UUID;

import com.studyhub.sth.application.dtos.discussao.DiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.NewDiscussaoDto;
import com.studyhub.sth.application.dtos.duvida.*;
import com.studyhub.sth.domain.entities.Duvida;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import java.util.Optional;

public interface IDuvidaService {
    List<DuvidaDto> findAll();
    DuvidaSolucoesDto findByIdWithSolucao(UUID id);
    DuvidaDto create(NewDuvidaDto newDuvidaDto);
    DuvidaDto update(UUID duvidaId, UpdateDuvidaDto updateDuvidaDto);
    void delete(UUID id);
}
