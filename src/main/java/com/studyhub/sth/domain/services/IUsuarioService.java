package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioUpdateDto;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;

import java.util.UUID;

public interface IUsuarioService {
    public UsuarioDto criar(UsuarioCreateDto novoUsuarioDto);
    public UsuarioDto atualizar(UUID usuarioId, UsuarioUpdateDto usuarioAtualizadoDto) throws ElementoNaoEncontradoExcecao;
    public UsuarioDto detalhar(UUID usuarioId) throws ElementoNaoEncontradoExcecao;
}
