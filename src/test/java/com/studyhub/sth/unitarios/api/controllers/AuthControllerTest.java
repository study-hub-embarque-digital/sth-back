package com.studyhub.sth.unitarios.api.controllers;

import com.studyhub.sth.api.controllers.AuthController;
import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioLoginDto;
import com.studyhub.sth.domain.services.IUsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.ArgumentMatchers.any;

public class AuthControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AuthController authController;

    @Mock
    private IUsuarioService usuarioService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void testSignup() throws Exception {
        // Cenário: Usuário se cadastra com sucesso
        UsuarioCreateDto usuarioNovoDto = new UsuarioCreateDto("John Doe", "john.doe@example.com", "password123", new Date());

        when(usuarioService.criar(any(UsuarioCreateDto.class))).thenReturn("Usuário criado com sucesso!");

        mockMvc.perform(post("/api/auth/signup")
                        .contentType("application/json")
                        .content("{\"nome\":\"John Doe\",\"email\":\"john.doe@example.com\",\"senha\":\"password123\",\"dataNascimento\":\"1990-01-01\"}"))
                .andExpect(status().isCreated()) // Espera o status 201
                .andExpect(content().string("Usuário criado com sucesso!")); // Espera a resposta do serviço
    }


    @Test
    public void testLogin() throws Exception {
        // Cenário: Login de usuário bem-sucedido
        UsuarioLoginDto usuarioLoginDto = new UsuarioLoginDto("john.doe@example.com", "password123");
        String fakeToken = "fake-jwt-token";

        when(usuarioService.login(any(UsuarioLoginDto.class))).thenReturn(fakeToken);

        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content("{\"email\":\"john.doe@example.com\",\"senha\":\"password123\"}"))
                .andExpect(status().isOk()) // Espera o status 200
                .andExpect(content().string(fakeToken)); // Espera o token gerado
    }

}
