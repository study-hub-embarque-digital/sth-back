package com.studyhub.sth.services.instituicaoEnsino;

import java.util.List;
import java.util.UUID;
import com.studyhub.sth.dtos.instituicaoEnsino.*;
import com.studyhub.sth.entities.InstituicaoEnsino;

public interface IInstituicaoEnsinoService {
    public List<InstituicaoEnsinoDto> findAll();
    public InstituicaoEnsinoDto findById(UUID id);
    public InstituicaoEnsinoDto update(UUID id, InstituicaoEnsinoUpdateDto dto);
    public InstituicaoEnsinoDto save(InstituicaoEnsinoCreateDto dto);
    public void delete(UUID id) ;
}

