package com.studyhub.sth.integracao.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.studyhub.sth.domain.before.enums.Periodo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.studyhub.sth.api.controllers.AlunosController;
import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
import com.studyhub.sth.domain.before.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.before.services.IAlunoService;

public class AlunosControllerTest {

    @InjectMocks
    private AlunosController alunosController;

    @Mock
    private IAlunoService alunoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarAluno_Sucesso() throws Exception {
        // Arrange
        AlunoCreateDto novoAlunoDto = new AlunoCreateDto();
        AlunoDto alunoDto = new AlunoDto();
        String token = "eyToken";
        when(alunoService.criar(novoAlunoDto)).thenReturn(token);

        // Act
        ResponseEntity<String> response = alunosController.criar(novoAlunoDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(token, response.getBody());
    }

    @Test
    public void testAtualizarAluno_Sucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID alunoId = UUID.randomUUID();
        AlunoUpdateDto alunoUpdateDto = new AlunoUpdateDto();
        AlunoDto alunoDto = new AlunoDto();
        when(alunoService.atualizar(alunoId, alunoUpdateDto)).thenReturn(alunoDto);

        // Act
        ResponseEntity<AlunoDto> response = alunosController.atualizar(alunoId, alunoUpdateDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(alunoDto, response.getBody());
    }

    @Test
    public void testDetalharAluno_Sucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID usuarioId = UUID.randomUUID();
        AlunoDto alunoDto = new AlunoDto();
        when(alunoService.detalhar(usuarioId)).thenReturn(alunoDto);

        // Act
        ResponseEntity<AlunoDto> response = alunosController.detalhar(usuarioId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(alunoDto, response.getBody());
    }

    @Test
    public void testListarTodosAlunos_Sucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        List<AlunoDto> alunos = Arrays.asList(new AlunoDto(), new AlunoDto());
        when(alunoService.listarTodos()).thenReturn(alunos);

        // Act
        ResponseEntity<List<AlunoDto>> response = alunosController.listarTodos();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(alunos, response.getBody());
    }

    @Test
    public void testObterAlunosPorPeriodo_Sucesso() {
        // Arrange
        Periodo periodo = Periodo.TERCEIRO;
        List<AlunoDto> alunos = Arrays.asList(new AlunoDto(), new AlunoDto());
        when(alunoService.listarPorPeriodo(periodo)).thenReturn(alunos);

        // Act
        ResponseEntity<List<AlunoDto>> response = alunosController.obterAlunosPorPeriodo(periodo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(alunos, response.getBody());
    }

    @Test
    public void testDetalharAluno_ElementoNaoEncontrado() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID usuarioId = UUID.randomUUID();
        when(alunoService.detalhar(usuarioId)).thenThrow(new ElementoNaoEncontradoExcecao("Aluno não encontrado"));

        // Act & Assert
        assertThrows(ElementoNaoEncontradoExcecao.class, () -> {
            alunosController.detalhar(usuarioId);
        });
    }
}
