package com.studyhub.sth.integracao.api.controllers;

import com.studyhub.sth.application.dtos.users.UsuarioUpdateDto;
import com.studyhub.sth.domain.entities.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class UsuarioTest {

    @Test
    public void testAtualizar_Sucesso() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setNome("Nome Antigo");
        usuario.setEmail("email@antigo.com");
        usuario.setSenha("senhaAntiga");
        usuario.setDataNascimento(new Date());

        UsuarioUpdateDto usuarioAtualizadoDto = new UsuarioUpdateDto();
        usuarioAtualizadoDto.setNome("Nome Novo");
        usuarioAtualizadoDto.setEmail("email@novo.com");
        usuarioAtualizadoDto.setSenha("senhaNova");
        usuarioAtualizadoDto.setDataNascimento(new Date());

        // Act
        usuario.atualizar(usuarioAtualizadoDto);

        // Assert
        assertEquals("Nome Novo", usuario.getNome());
        assertEquals("email@novo.com", usuario.getEmail());
        assertEquals("senhaNova", usuario.getSenha());
        assertEquals(usuarioAtualizadoDto.getDataNascimento(), usuario.getDataNascimento());
    }
}
