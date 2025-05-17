package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioLoginDto;
import com.studyhub.sth.application.dtos.users.UsuarioUpdateDto;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface IUsuarioService {
    public String criar(UsuarioCreateDto novoUsuarioDto) throws Exception;

    String login(UsuarioLoginDto usuarioLoginDto) throws Exception;

    public UsuarioDto atualizar(UUID usuarioId, UsuarioUpdateDto usuarioAtualizadoDto) throws ElementoNaoEncontradoExcecao, IOException;
    public UsuarioDto detalhar(UUID usuarioId) throws ElementoNaoEncontradoExcecao;
    public String atualizarFoto(UUID usuarioId, MultipartFile foto) throws ElementoNaoEncontradoExcecao, IOException ;

}
