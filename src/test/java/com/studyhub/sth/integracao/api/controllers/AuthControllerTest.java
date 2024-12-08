package com.studyhub.sth.integracao.api.controllers;

import com.studyhub.sth.api.controllers.AuthController;
import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioLoginDto;
import com.studyhub.sth.domain.services.IUsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

public class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private IUsuarioService usuarioService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignup_Sucesso() throws Exception {
        // Arrange
        UsuarioCreateDto usuarioNovoDto = new UsuarioCreateDto();
        String expectedResponse = "Usuário criado com sucesso";
        when(usuarioService.criar(usuarioNovoDto)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = authController.signup(usuarioNovoDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testSignup_Exception() throws Exception {
        // Arrange
        UsuarioCreateDto usuarioNovoDto = new UsuarioCreateDto();
        when(usuarioService.criar(usuarioNovoDto)).thenThrow(new Exception("Erro ao criar usuário"));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            authController.signup(usuarioNovoDto);
        });
        assertEquals("Erro ao criar usuário", exception.getMessage());
    }

    @Test
    public void testLogin_Sucesso() throws Exception {
        // Arrange
        UsuarioLoginDto usuarioLoginDto = new UsuarioLoginDto();
        String expectedToken = "token_jwt";
        when(usuarioService.login(usuarioLoginDto)).thenReturn(expectedToken);

        // Act
        ResponseEntity<String> response = authController.login(usuarioLoginDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedToken, response.getBody());
    }

    @Test
    public void testLogin_Exception() throws Exception {
        // Arrange
        UsuarioLoginDto usuarioLoginDto = new UsuarioLoginDto();
        when(usuarioService.login(usuarioLoginDto)).thenThrow(new Exception("Credenciais inválidas"));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            authController.login(usuarioLoginDto);
        });
        assertEquals("Credenciais inválidas", exception.getMessage());
    }
}
