package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.representante.RepresentanteCreateDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteUpdateDto;
import com.studyhub.sth.domain.entities.Empresa;
import com.studyhub.sth.domain.entities.Representante;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IRepresentanteService;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.repositories.EmpresaRepository;
import com.studyhub.sth.domain.repositories.IRepresentanteRepositorio;

import com.studyhub.sth.domain.repositories.IUsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public RepresentanteDto criarRepresentante(RepresentanteCreateDto dto) throws ElementoNaoEncontradoExcecao {
        Usuario usuario = this.mapper.map(dto.getNovoUsuarioDto(), Usuario.class);
        this.usuarioRepositorio.save(usuario);

        Empresa empresa = this.empresaRepository.findById(dto.getEmpresaId()).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Empresa não encontrada"));

        Representante representante = new Representante();
        representante.setUsuario(usuario);
        representante.setEmpresa(empresa);

        this.representanteRepository.save(representante);
        return this.mapper.map(representante, RepresentanteDto.class);
    }

    @Override
    @Transactional
    public List<RepresentanteDto> listarRepresentantes() {
        var lista = this.representanteRepository.findAll();
        return lista.stream().map(representante -> this.mapper.map(representante,RepresentanteDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RepresentanteDto obterRepresentantePorId(UUID id) {
        var representante = this.representanteRepository.findById(id).orElseThrow(() -> new RuntimeException("Representante não encontrado"));
        return this.mapper.map(representante,RepresentanteDto.class);
    }

    @Override
    @Transactional
    public RepresentanteDto atualizarRepresentante(UUID id, RepresentanteUpdateDto dto) {
        var representante = this.representanteRepository.findById(id).orElseThrow(() -> new RuntimeException("Representante não encontrado"));
        if (dto.getUsuarioDto() != null) {
            if (dto.getUsuarioDto().getNome() != null) {
                representante.getUsuario().setNome(dto.getUsuarioDto().getNome());
            }
            if (dto.getUsuarioDto().getEmail() != null) {
                representante.getUsuario().setEmail(dto.getUsuarioDto().getEmail());
            }
            if (dto.getUsuarioDto().getSenha() != null) {
                representante.getUsuario().setSenha(dto.getUsuarioDto().getSenha());
            }
            if (dto.getUsuarioDto().getDataNascimento() != null) {
                representante.getUsuario().setDataNascimento(dto.getUsuarioDto().getDataNascimento());
            }
        }
        if (dto.getEmpresaId() != null){
            representante.setRepresentanteId(dto.getEmpresaId());
        }
        this.representanteRepository.save(representante);
        return this.mapper.map(representante, RepresentanteDto.class);
    }

    @Override
    @Transactional
    public void deletarRepresentante(UUID id) {
        var representante = this.representanteRepository.findById(id).orElseThrow(() -> new RuntimeException("Representante não encontrado"));
        representanteRepository.delete(representante);
    }
}
