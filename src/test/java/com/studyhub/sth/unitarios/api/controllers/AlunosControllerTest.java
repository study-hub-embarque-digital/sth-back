package com.studyhub.sth.unitarios.api.controllers;

import com.studyhub.sth.api.controllers.AlunosController;
import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IAlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AlunosControllerTest {

    @Mock
    private IAlunoService alunoService;

    @InjectMocks
    private AlunosController alunosController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criar_AlunoComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        AlunoCreateDto novoAlunoDto = new AlunoCreateDto();
        AlunoDto alunoCriado = new AlunoDto();
        when(alunoService.criar(novoAlunoDto)).thenReturn(alunoCriado);

        // Act
        ResponseEntity<AlunoDto> response = alunosController.criar(novoAlunoDto);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(alunoCriado, response.getBody());
        verify(alunoService, times(1)).criar(novoAlunoDto);
    }

    @Test
    void atualizar_AlunoComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID alunoId = UUID.randomUUID();
        AlunoUpdateDto alunoAtualizadoDto = new AlunoUpdateDto();
        AlunoDto alunoAtualizado = new AlunoDto();
        when(alunoService.atualizar(alunoId, alunoAtualizadoDto)).thenReturn(alunoAtualizado);

        // Act
        ResponseEntity<AlunoDto> response = alunosController.atualizar(alunoId, alunoAtualizadoDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(alunoAtualizado, response.getBody());
        verify(alunoService, times(1)).atualizar(alunoId, alunoAtualizadoDto);
    }

    @Test
    void detalhar_AlunoComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID usuarioId = UUID.randomUUID();
        AlunoDto alunoDetalhado = new AlunoDto();
        when(alunoService.detalhar(usuarioId)).thenReturn(alunoDetalhado);

        // Act
        ResponseEntity<AlunoDto> response = alunosController.detalhar(usuarioId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(alunoDetalhado, response.getBody());
        verify(alunoService, times(1)).detalhar(usuarioId);
    }

    @Test
    void listarTodos_AlunosComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        List<AlunoDto> alunos = Arrays.asList(new AlunoDto(), new AlunoDto());
        when(alunoService.listarTodos()).thenReturn(alunos);

        // Act
        ResponseEntity<List<AlunoDto>> response = alunosController.listarTodos();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(alunos, response.getBody());
        verify(alunoService, times(1)).listarTodos();
    }

    @Test
    void obterAlunosPorPeriodo_ComSucesso() {
        // Arrange
        int periodo = 2;
        List<AlunoDto> alunosPorPeriodo = Arrays.asList(new AlunoDto(), new AlunoDto());
        when(alunoService.listarPorPeriodo(periodo)).thenReturn(alunosPorPeriodo);

        // Act
        ResponseEntity<List<AlunoDto>> response = alunosController.obterAlunosPorPeriodo(periodo);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(alunosPorPeriodo, response.getBody());
        verify(alunoService, times(1)).listarPorPeriodo(periodo);
    }
}
