package com.studyhub.sth.services.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import com.studyhub.sth.repositories.IUsuarioRepositorio;
import com.studyhub.sth.dtos.users.NovoUsuarioDto;
import org.springframework.stereotype.Service;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.entities.Usuario;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private IMapper mapper;

    @Override
    public Usuario criar(NovoUsuarioDto novoUsuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setNome(novoUsuarioDto.nome());
        usuario.setEmail(novoUsuarioDto.email());
        usuario.setSenha(novoUsuarioDto.senha());
        usuario.setDataNascimento(novoUsuarioDto.dataNascimento());

        this.usuarioRepositorio.save(usuario);

        return usuario;
    }

    @Override
    public Usuario atualizar() {
        return null;
    }

    @Override
    public Usuario detalhar() {
        return null;
    }

    @Override
    public Usuario listar() {
        return null;
    }
}
