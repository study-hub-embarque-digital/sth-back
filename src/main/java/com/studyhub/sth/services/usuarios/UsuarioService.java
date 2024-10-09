package com.studyhub.sth.services.usuarios;

import com.studyhub.sth.dtos.users.UsuarioAtualizadoDto;
import com.studyhub.sth.dtos.users.UsuarioDto;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.studyhub.sth.repositories.IUsuarioRepositorio;
import com.studyhub.sth.dtos.users.NovoUsuarioDto;
import org.springframework.stereotype.Service;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.entities.Usuario;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private IMapper mapper;

    @Override
    public UsuarioDto criar(NovoUsuarioDto novoUsuarioDto) {
        Usuario usuario = this.mapper.map(novoUsuarioDto, Usuario.class);

        this.usuarioRepositorio.save(usuario);

        return this.mapper.map(usuario, UsuarioDto.class);
    }

    @Override
    public UsuarioDto atualizar(UUID usuarioId, UsuarioAtualizadoDto usuarioAtualizadoDto) throws ElementoNaoEncontradoExcecao {
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
