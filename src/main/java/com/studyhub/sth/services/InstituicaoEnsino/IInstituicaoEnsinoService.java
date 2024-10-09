package com.studyhub.sth.services.InstituicaoEnsino;

import java.util.List;
import java.util.UUID;
import com.studyhub.sth.dtos.InstituicaoEnsino.*;
import com.studyhub.sth.entities.InstituicaoEnsino;

public interface IInstituicaoEnsinoService {
    public List<InstituicaoEnsino> findAll();
    public InstituicaoEnsino findById(UUID id);
    public InstituicaoEnsino update(UUID InstituicaoEnsinoId, UpdateInstituicaoEnsinoDto InstituicaoEnsinoDto);
    public InstituicaoEnsino save(InstituicaoEnsino instituicao);
    public void delete(UUID id) ;
}

