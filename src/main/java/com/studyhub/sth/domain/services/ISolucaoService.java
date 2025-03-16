package com.studyhub.sth.domain.services;

import java.util.List;

import com.studyhub.sth.application.dtos.solucao.SolucaoDto;

public interface ISolucaoService {
    List<SolucaoDto> findAll();
    
}
