package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.users.UsuarioUpdateDto;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import com.studyhub.sth.domain.repositories.IUsuarioRepositorio;
import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import org.springframework.stereotype.Service;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.entities.Usuario;

import java.util.UUID;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private IMapper mapper;

    @Override
    public UsuarioDto criar(UsuarioCreateDto novoUsuarioDto) {
        Usuario usuario = this.mapper.map(novoUsuarioDto, Usuario.class);

        this.usuarioRepositorio.save(usuario);

        return this.mapper.map(usuario, UsuarioDto.class);
    }

    @Override
    public UsuarioDto atualizar(UUID usuarioId, UsuarioUpdateDto usuarioAtualizadoDto) throws ElementoNaoEncontradoExcecao {
        Usuario usuario = this.usuarioRepositorio.findById(usuarioId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar o usuário selecionado."));
        usuario.atualizar(usuarioAtualizadoDto);

        this.usuarioRepositorio.save(usuario);

        return this.mapper.map(usuario, UsuarioDto.class);
    }

    @Override
    public UsuarioDto detalhar(UUID usuarioId) throws ElementoNaoEncontradoExcecao {
        Usuario usuario = this.usuarioRepositorio.findById(usuarioId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar o usuário selecionado."));

        return this.mapper.map(usuario, UsuarioDto.class);
    }
}
