package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IAlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AlunosControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IAlunoService alunoService;

    private AlunosController alunosController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        alunosController = new AlunosController();
        mockMvc = MockMvcBuilders.standaloneSetup(alunosController).build();
    }

    @Test
    public void testCriarAluno() throws Exception {
        // Arrange
        AlunoCreateDto alunoCreateDto = new AlunoCreateDto();
        alunoCreateDto.setCurso("Engenharia");
        alunoCreateDto.setPeriodo(2);
        alunoCreateDto.setInstituicaoEnsinoId(UUID.randomUUID());

        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setAlunoId(UUID.randomUUID());
        alunoDto.setCurso("Engenharia");
        alunoDto.setPeriodo(2);

        when(alunoService.criar(any(AlunoCreateDto.class))).thenReturn(alunoDto);

        // Act & Assert
        mockMvc.perform(post("/api/alunos")
                        .contentType("application/json")
                        .content("{ \"curso\": \"Engenharia\", \"periodo\": 2, \"instituicaoEnsinoId\": \"b95a7b6c-1ec5-44fa-a76a-2eabff295c51\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.curso").value("Engenharia"))
                .andExpect(jsonPath("$.periodo").value(2));

        verify(alunoService, times(1)).criar(any(AlunoCreateDto.class));
    }

    @Test
    public void testDetalharAluno() throws Exception {
        // Arrange
        UUID alunoId = UUID.randomUUID();
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setAlunoId(alunoId);
        alunoDto.setCurso("Engenharia");
        alunoDto.setPeriodo(2);

        when(alunoService.detalhar(alunoId)).thenReturn(alunoDto);

        // Act & Assert
        mockMvc.perform(get("/api/alunos/{usuarioId}", alunoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.curso").value("Engenharia"))
                .andExpect(jsonPath("$.periodo").value(2));

        verify(alunoService, times(1)).detalhar(alunoId);
    }

    @Test
    public void testAtualizarAluno() throws Exception {
        // Arrange
        UUID alunoId = UUID.randomUUID();
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setAlunoId(alunoId);
        alunoDto.setCurso("Engenharia");
        alunoDto.setPeriodo(2);

        when(alunoService.atualizar(eq(alunoId), any())).thenReturn(alunoDto);

        // Act & Assert
        mockMvc.perform(put("/api/alunos/{alunoId}", alunoId)
                        .contentType("application/json")
                        .content("{ \"curso\": \"Engenharia\", \"periodo\": 2 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.curso").value("Engenharia"))
                .andExpect(jsonPath("$.periodo").value(2));

        verify(alunoService, times(1)).atualizar(eq(alunoId), any());
    }

    @Test
    public void testListarTodosAlunos() throws Exception {
        // Arrange
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setAlunoId(UUID.randomUUID());
        alunoDto.setCurso("Engenharia");
        alunoDto.setPeriodo(2);

        when(alunoService.listarTodos()).thenReturn(List.of(alunoDto));

        // Act & Assert
        mockMvc.perform(get("/api/alunos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].curso").value("Engenharia"))
                .andExpect(jsonPath("$[0].periodo").value(2));

        verify(alunoService, times(1)).listarTodos();
    }
}
