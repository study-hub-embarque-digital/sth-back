package com.studyhub.sth.domain.services;

import java.util.List;
import java.util.UUID;

import com.studyhub.sth.application.dtos.empresas.EmpresaCreateDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaListDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaUpdateDto;
import com.studyhub.sth.application.dtos.graficos.EmpresaPorCicloDTO;

public interface IEmpresaService {
    public List<EmpresaDto> findAll();
    public List<EmpresaListDto> list();
    public EmpresaDto findById(UUID id);
    public EmpresaDto update(UUID empresaId, EmpresaUpdateDto empresaDto);
    public EmpresaDto save(EmpresaCreateDto empresaCreateDto);
    public void delete(UUID id);
    public List<EmpresaPorCicloDTO> findEmpresasPorCiclo();

}
