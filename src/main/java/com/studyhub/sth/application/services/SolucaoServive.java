package com.studyhub.sth.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyhub.sth.application.dtos.solucao.SolucaoDto;
import com.studyhub.sth.domain.repositories.ISolucaoRepository;
import com.studyhub.sth.domain.services.ISolucaoService;
import com.studyhub.sth.libs.mapper.IMapper;

@Service
public class SolucaoServive implements ISolucaoService{
    @Autowired
    private ISolucaoRepository solucaoRepository;

    @Autowired
    private IMapper mapper;

    @Override
    public List<SolucaoDto> findAll() {
        return this.solucaoRepository
                .findAll()
                .stream()
                .map(d -> {
                    SolucaoDto dto = this.mapper.map(d, SolucaoDto.class);
                    dto.setNomeUsuario(d.getUsuario().getNome());
                    return dto;
                })
                .toList();
    } 
    
}
