package com.studyhub.sth.services.empresa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.studyhub.sth.dtos.empresas.EmpresaCreateDto;
import com.studyhub.sth.dtos.empresas.EmpresaDto;
import com.studyhub.sth.dtos.empresas.EmpresaUpdateDto;
import com.studyhub.sth.entities.Empresa;

public interface IEmpresaService {
    public List<EmpresaDto> findAll();
    public EmpresaDto findById(UUID id);
    public EmpresaDto update(UUID empresaId, EmpresaUpdateDto empresaDto);
    public EmpresaDto save(EmpresaCreateDto empresaCreateDto);
    public void delete(UUID id);
}
