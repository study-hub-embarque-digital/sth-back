package com.studyhub.sth.services.Empresa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.studyhub.sth.dtos.Empresas.UpdateEmpresaDto;
import com.studyhub.sth.entities.Empresa;

public interface IEmpresaService {
    public List<Empresa> findAll();
    public Optional<Empresa> findById(UUID id);
    public Empresa update(UUID empresaId, UpdateEmpresaDto empresaDto);
    public Empresa save(Empresa empresa);
    public void delete(UUID id);
}
