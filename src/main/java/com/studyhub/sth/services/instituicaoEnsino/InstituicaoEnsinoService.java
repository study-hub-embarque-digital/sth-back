package com.studyhub.sth.services.instituicaoEnsino;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studyhub.sth.dtos.instituicaoEnsino.*;
import com.studyhub.sth.entities.InstituicaoEnsino;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.repositories.InstituicaoEnsinoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class InstituicaoEnsinoService implements IInstituicaoEnsinoService{
    @Autowired
    private IMapper mapper;

    @Autowired
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    @Override
    public List<InstituicaoEnsinoDto> findAll() {
        var lista = this.instituicaoEnsinoRepository.findAll();
        return lista.stream()
                .map(instituicaoEnsino -> this.mapper.map(instituicaoEnsino, InstituicaoEnsinoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public InstituicaoEnsinoDto findById(UUID id) {
        var instituicao = this.instituicaoEnsinoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("IE não encontrada!"));
        return this.mapper.map(instituicao, InstituicaoEnsinoDto.class);
    }
    @Override
    @Transactional
    public InstituicaoEnsinoDto save(InstituicaoEnsinoCreateDto dto) {
        InstituicaoEnsino instituicao = this.mapper.map(dto, InstituicaoEnsino.class);
        this.instituicaoEnsinoRepository.save(instituicao);
        return this.mapper.map(instituicao, InstituicaoEnsinoDto.class);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        var instituicao = this.instituicaoEnsinoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("IE não encontrada!"));
        this.instituicaoEnsinoRepository.delete(instituicao);
    }

    @Override
    @Transactional
    public InstituicaoEnsinoDto update(UUID id, InstituicaoEnsinoUpdateDto dto) {
       var instituicao = instituicaoEnsinoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("IE não encontrada!"));
        if (dto.getCoordenador() != null) {
            instituicao.setCoordenador(dto.getCoordenador());
        }
        if (dto.getNome() != null) {
            instituicao.setNome(dto.getNome());
        }
        if (dto.getEndereco() != null) {
            instituicao.setEndereco(dto.getEndereco());
        }
        this.instituicaoEnsinoRepository.save(instituicao);
        return this.mapper.map(instituicao, InstituicaoEnsinoDto.class);
    }
}
