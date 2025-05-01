package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.empregador.EmpregadorCreateDto;
import com.studyhub.sth.application.dtos.empregador.EmpregadorListDto;
import com.studyhub.sth.application.dtos.empregador.EmpregadorUpdateDto;
import com.studyhub.sth.domain.entities.Empregador;
import com.studyhub.sth.domain.services.IEmpregadorService;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.repositories.IEmpregadorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmpregadorService  implements IEmpregadorService {

    @Autowired
    private IMapper mapper;

    @Autowired
    private IEmpregadorRepository empregadorRepository;

    @Override
    @Transactional
    public EmpregadorListDto criar(EmpregadorCreateDto dto) {
        Empregador empregador = this.mapper.map(dto, Empregador.class);
        this.empregadorRepository.save(empregador);
        return this.mapper.map(empregador, EmpregadorListDto.class);
    }

    @Override
    public List<EmpregadorListDto> listar() {
        var lista = this.empregadorRepository.findAll();
        return lista.stream().map(empregador -> this.mapper.map(empregador, EmpregadorListDto.class)).collect(Collectors.toList());
    }

    @Override
    public EmpregadorListDto buscarPorId(UUID id) {
        var empregador = this.empregadorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empregador não encontrado"));
        return this.mapper.map(empregador, EmpregadorListDto.class);
    }

    @Override
    @Transactional
    public EmpregadorListDto atualizar(UUID id, EmpregadorUpdateDto dto) {
        var empregador = this.empregadorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empregador não encontrado"));
        if(dto.getNomeGestor() != null){
            empregador.setNomeGestor(dto.getNomeGestor());
        }
        if(dto.getCargoGestor() != null){
            empregador.setCargoGestor(dto.getCargoGestor());
        }
        if(dto.getEmailGestor() != null){
            empregador.setEmailGestor(dto.getEmailGestor());
        }
        if(dto.getAtividadesDesenvolvidas() != null){
            empregador.setAtividadesDesenvolvidas(dto.getAtividadesDesenvolvidas());
        }
        if(dto.getTipoVinculo() != null){
            empregador.setTipoVinculo(dto.getTipoVinculo());
        }
        if(dto.getCargoDetalhado() != null){
            empregador.setCargoDetalhado(dto.getCargoDetalhado());
        }
        this.empregadorRepository.save(empregador);
        return this.mapper.map(empregador, EmpregadorListDto.class);
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        var empregador = this.empregadorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empregador não encontrado"));
        this.empregadorRepository.delete(empregador);
    }
}
