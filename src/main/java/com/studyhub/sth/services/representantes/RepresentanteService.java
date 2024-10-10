package com.studyhub.sth.services.representantes;

import com.studyhub.sth.dtos.representante.NovoRepresentanteDto;
import com.studyhub.sth.entities.Empresa;
import com.studyhub.sth.entities.Representante;
import com.studyhub.sth.entities.Usuario;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.repositories.EmpresaRepository;
import com.studyhub.sth.repositories.IRepresentanteRepositorio;

import com.studyhub.sth.repositories.IUsuarioRepositorio;
import com.studyhub.sth.services.Empresa.IEmpresaService;
import com.studyhub.sth.services.usuarios.IUsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RepresentanteService implements IRepresentanteService {
    @Autowired
    private IRepresentanteRepositorio representanteRepository;
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private IMapper mapper;

    @Override
    @Transactional
    public Representante criarRepresentante(NovoRepresentanteDto dto) throws ElementoNaoEncontradoExcecao {
        Usuario usuario = this.mapper.map(dto.getNovoUsuarioDto(), Usuario.class);
        this.usuarioRepositorio.save(usuario);

        Empresa empresa = this.empresaRepository.findById(dto.getEmpresaId()).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Empresa não encontrada"));

        Representante representante = new Representante();
        representante.setUsuario(usuario);
        representante.setEmpresa(empresa);

        return representanteRepository.save(representante);
    }

    @Override
    @Transactional
    public List<Representante> listarRepresentantes() {
        return representanteRepository.findAll();
    }

    @Override
    @Transactional
    public Representante obterRepresentantePorId(UUID id) {
        return representanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Representante não encontrado"));
    }

    @Override
    @Transactional
    public Representante atualizarRepresentante(UUID id, NovoRepresentanteDto dto) {
        Representante representante = obterRepresentantePorId(id);
        representante.setUsuario(new Usuario());
        //representante.setEmpresaId(dto.getEmpresaId());
        return representanteRepository.save(representante);
    }

    @Override
    public void deletarRepresentante(UUID id) {
        representanteRepository.deleteById(id);
    }
}
