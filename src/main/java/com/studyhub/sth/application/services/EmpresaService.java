package com.studyhub.sth.application.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.studyhub.sth.application.dtos.empresas.EmpresaCreateDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import com.studyhub.sth.domain.services.IEmpresaService;
import com.studyhub.sth.libs.mapper.IMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyhub.sth.application.dtos.empresas.EmpresaUpdateDto;
import com.studyhub.sth.domain.entities.Empresa;
import com.studyhub.sth.domain.repositories.IEmpresaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmpresaService implements IEmpresaService {
    @Autowired
    private IEmpresaRepository IEmpresaRepository;

    @Autowired
    private IMapper mapper;

    @Override
    public List<EmpresaDto> findAll() {
        var lista = IEmpresaRepository.findAll();
        return lista.stream().map(
                empresa -> this.mapper.map(empresa, EmpresaDto.class)).collect(Collectors.toList());
    }

    @Override
    public EmpresaDto findById(UUID id) {
        var empresa = this.IEmpresaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("A empresa não foi encontrada!"));
        return this.mapper.map(empresa, EmpresaDto.class);
    }

    @Override
    @Transactional
    public EmpresaDto update(UUID empresaId, EmpresaUpdateDto dto) {
        var empresa = IEmpresaRepository.findById(empresaId).orElseThrow(()-> new EntityNotFoundException("A empresa não foi encontrada!"));
        if(dto.getEmail() != null){
            empresa.setEmail(dto.getEmail());
        }
        if(dto.getTelefone() != null){
            empresa.setEmail(dto.getTelefone());
        }
        if(dto.getNomeFantasia() != null){
            empresa.setEmail(dto.getNomeFantasia());
        }
        IEmpresaRepository.save(empresa);
        return this.mapper.map(empresa, EmpresaDto.class);
    }

    @Override
    @Transactional
    public EmpresaDto save(EmpresaCreateDto dto) {
        Empresa empresa = this.mapper.map(dto, Empresa.class);
        IEmpresaRepository.save(empresa);
        return this.mapper.map(empresa, EmpresaDto.class);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        var empresa = this.IEmpresaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("A empresa não foi encontrada!"));
        IEmpresaRepository.deleteById(id);
    }

}
