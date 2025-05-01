package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.empregador.EmpregadorCreateDto;
import com.studyhub.sth.application.dtos.empregador.EmpregadorListDto;
import com.studyhub.sth.application.dtos.empregador.EmpregadorUpdateDto;

import java.util.List;
import java.util.UUID;

public interface IEmpregadorService {
    EmpregadorListDto criar(EmpregadorCreateDto dto);

    List<EmpregadorListDto> listar();

    EmpregadorListDto buscarPorId(UUID id);

    EmpregadorListDto atualizar(UUID id, EmpregadorUpdateDto dto);

    void deletar(UUID id);
}