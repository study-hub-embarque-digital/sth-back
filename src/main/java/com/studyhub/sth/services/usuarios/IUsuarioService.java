package com.studyhub.sth.services.usuarios;

import com.studyhub.sth.dtos.users.UsuarioCreateDto;
import com.studyhub.sth.dtos.users.UsuarioUpdateDto;
import com.studyhub.sth.dtos.users.UsuarioDto;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;

import java.util.UUID;

public interface IUsuarioService {
    public UsuarioDto criar(UsuarioCreateDto novoUsuarioDto);
    public UsuarioDto atualizar(UUID usuarioId, UsuarioUpdateDto usuarioAtualizadoDto) throws ElementoNaoEncontradoExcecao;
    public UsuarioDto detalhar(UUID usuarioId) throws ElementoNaoEncontradoExcecao;
}
