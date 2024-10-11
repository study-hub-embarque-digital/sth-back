package com.studyhub.sth.services.Empresa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.studyhub.sth.dtos.Empresas.EmpresaDto;
import com.studyhub.sth.dtos.Empresas.NovoEmpresaDto;
import com.studyhub.sth.libs.mapper.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyhub.sth.dtos.Empresas.UpdateEmpresaDto;
import com.studyhub.sth.entities.Empresa;
import com.studyhub.sth.entities.Usuario;
import com.studyhub.sth.repositories.EmpresaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmpresaService implements IEmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private IMapper mapper;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> findById(UUID id) {
        return empresaRepository.findById(id);
    }


    public Empresa update(UUID empresaId, UpdateEmpresaDto empresaDto) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(empresaId);
        
        if (optionalEmpresa.isPresent()) {
            Empresa empresa = optionalEmpresa.get();
            empresa.setRazaoSocial(empresaDto.getRazaoSocial());
            empresa.setNomeFantasia(empresaDto.getNomeFantasia());
            empresa.setTelefone(empresaDto.getTelefone());
            empresa.setEmail(empresaDto.getEmail());
            empresa.setCnpj(empresaDto.getCnpj());
            return empresaRepository.save(empresa);
        } else {
            throw new EntityNotFoundException("Empresa não encontrada com o ID: " + empresaId);
        }
    }

    public Empresa save(NovoEmpresaDto novoEmpresaDto) {
        Empresa newEmpresa = this.mapper.map(novoEmpresaDto, Empresa.class);
        return empresaRepository.save(newEmpresa);
    }

    public void delete(UUID id) {
        empresaRepository.deleteById(id);
    }

}
