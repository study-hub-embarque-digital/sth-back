package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.users.UsuarioLoginDto;
import com.studyhub.sth.application.dtos.users.UsuarioUpdateDto;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import com.studyhub.sth.domain.repositories.IUsuarioRepository;
import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.entities.Usuario;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepositorio;
    @Autowired
    private IMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    @Override
    public String criar(UsuarioCreateDto novoUsuarioDto) throws Exception {
        Optional<Usuario> usuario = usuarioRepositorio.findByEmail(novoUsuarioDto.getEmail());

        if (usuario.isPresent()) throw new Exception("Já existe um usuário cadastrado com este email.");

        Usuario usuarioNovo = this.mapper.map(novoUsuarioDto, Usuario.class);
        usuarioNovo.setSenha(passwordEncoder.encode(novoUsuarioDto.getSenha()));

        this.usuarioRepositorio.save(usuarioNovo);

        return tokenService.generateToken(usuarioNovo);
    }

    @Override
    public String login(UsuarioLoginDto usuarioLoginDto) throws Exception {
        Usuario usuario = this.usuarioRepositorio.findByEmail(usuarioLoginDto.getEmail()).orElseThrow(() -> new Exception("Email ou senha incorretos"));

        if (!passwordEncoder.matches(usuarioLoginDto.getSenha(), usuario.getSenha())) throw new Exception("Usuário ou senha incorretos");

        return tokenService.generateToken(usuario);
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
