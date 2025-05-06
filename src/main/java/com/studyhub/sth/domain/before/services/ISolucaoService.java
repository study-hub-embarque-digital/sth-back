package com.studyhub.sth.domain.before.services;

import java.util.List;
import java.util.UUID;

import com.studyhub.sth.application.dtos.solucao.*;

public interface ISolucaoService {

    List<SolucaoDto> findAll();
    SolucaoDto create(CreateSolucaoDto solucaoDto);
    SolucaoDto update(UpdateSolucaoDto solucaoDto, UUID solucao);
    void delete(UUID id);
}
