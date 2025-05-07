package com.studyhub.sth.application.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.studyhub.sth.application.dtos.enderecos.EnderecoDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoCreateDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoListDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoUpdateDto;
import com.studyhub.sth.domain.entities.Endereco;
import com.studyhub.sth.domain.repositories.IEnderecoRepository;
import com.studyhub.sth.domain.services.IInstituicaoEnsinoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studyhub.sth.domain.entities.InstituicaoEnsino;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.repositories.InstituicaoEnsinoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class InstituicaoEnsinoService implements IInstituicaoEnsinoService {
    @Autowired
    private IMapper mapper;

    @Autowired
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    @Autowired
    private IEnderecoRepository enderecoRepository;

    @Override
    public List<InstituicaoEnsinoDto> findAll() {
        var lista = this.instituicaoEnsinoRepository.findAll();
        return lista.stream()
                .map(instituicaoEnsino -> this.mapper.map(instituicaoEnsino, InstituicaoEnsinoDto.class))
                .collect(Collectors.toList());
    }

    public List<InstituicaoEnsinoListDto> list() {
        var lista = this.instituicaoEnsinoRepository.findAll();
        return lista.stream()
                .map(instituicaoEnsino -> this.mapper.map(instituicaoEnsino, InstituicaoEnsinoListDto.class))
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
        Endereco endereco = new Endereco(1, 1, dto.getEndereco().getCep(), dto.getEndereco().getLogradouro(), dto.getEndereco().getCidade(), dto.getEndereco().getEstado(), dto.getEndereco().getComplemento(), dto.getEndereco().getNumero());
        this.enderecoRepository.save(endereco);

        InstituicaoEnsino instituicao = new InstituicaoEnsino(dto.getCoordenador(), dto.getRazaoSocial(), dto.getNomeFantasia(), dto.getTelefone(), dto.getEmail(), dto.getCnpj(), dto.getSite(), endereco);
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
//        if (dto.getNome() != null) {
//            instituicao.setNome(dto.getNome());
//        }
        if (dto.getEmail() != null){
            instituicao.setEmail(dto.getEmail());
        }
        if (dto.getTelefone() != null){
            instituicao.setTelefone(dto.getTelefone());
        }
        if (dto.getSite() != null){
            instituicao.setSite(dto.getSite());
        }
        if(dto.getIsActive() != null){
            instituicao.setIsActive(dto.getIsActive());
        }
        this.instituicaoEnsinoRepository.save(instituicao);
        return this.mapper.map(instituicao, InstituicaoEnsinoDto.class);
    }
}
