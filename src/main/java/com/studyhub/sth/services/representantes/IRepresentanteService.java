package com.studyhub.sth.services.representantes;

import com.studyhub.sth.dtos.representante.NovoRepresentanteDto;
import com.studyhub.sth.entities.Representante;
import com.studyhub.sth.entities.Usuario;
import com.studyhub.sth.repositories.IRepresentanteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IRepresentanteService {
    @Autowired
    private IRepresentanteRepositorio representanteRepository;

    public Representante criarRepresentante(NovoRepresentanteDto dto) {
        Representante representante = new Representante();
        representante.setUsuario(new Usuario());
        representante.setEmpresaId(dto.getEmpresaId());
        return representanteRepository.save(representante);
    }

    public List<Representante> listarRepresentantes() {
        return representanteRepository.findAll();
    }

    public Representante obterRepresentantePorId(UUID id) {
        return representanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Representante não encontrado"));
    }

    public Representante atualizarRepresentante(UUID id, NovoRepresentanteDto dto) {
        Representante representante = obterRepresentantePorId(id);
        representante.setUsuario(new Usuario());
        representante.setEmpresaId(dto.getEmpresaId());
        return representanteRepository.save(representante);
    }

    public void deletarRepresentante(UUID id) {
        representanteRepository.deleteById(id);
    }
}
