package com.studyhub.sth.domain.services;

import java.util.List;
import java.util.UUID;

import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoCreateDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoUpdateDto;

public interface IInstituicaoEnsinoService {
    public List<InstituicaoEnsinoDto> findAll();
    public InstituicaoEnsinoDto findById(UUID id);
    public InstituicaoEnsinoDto update(UUID id, InstituicaoEnsinoUpdateDto dto);
    public InstituicaoEnsinoDto save(InstituicaoEnsinoCreateDto dto);
    public void delete(UUID id) ;
}

