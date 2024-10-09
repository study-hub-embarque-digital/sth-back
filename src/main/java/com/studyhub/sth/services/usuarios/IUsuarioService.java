package com.studyhub.sth.services.usuarios;

import com.studyhub.sth.dtos.users.NovoUsuarioDto;
import com.studyhub.sth.dtos.users.UsuarioAtualizadoDto;
import com.studyhub.sth.dtos.users.UsuarioDto;
import com.studyhub.sth.entities.Usuario;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;

import java.util.UUID;

public interface IUsuarioService {
    public UsuarioDto criar(NovoUsuarioDto novoUsuarioDto);
    public UsuarioDto atualizar(UUID usuarioId, UsuarioAtualizadoDto usuarioAtualizadoDto) throws ElementoNaoEncontradoExcecao;
    public UsuarioDto detalhar(UUID usuarioId) throws ElementoNaoEncontradoExcecao;
}
