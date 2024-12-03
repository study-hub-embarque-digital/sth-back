package com.studyhub.sth.unitarios.api.controllers;

import com.studyhub.sth.api.controllers.RepresentanteController;
import com.studyhub.sth.application.dtos.representante.RepresentanteCreateDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteUpdateDto;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IRepresentanteService;
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

class RepresentanteControllerTest {

    @Mock
    private IRepresentanteService representanteService;

    @InjectMocks
    private RepresentanteController representanteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criar_RepresentanteComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        RepresentanteCreateDto novoRepresentanteDto = new RepresentanteCreateDto();
        RepresentanteDto representanteCriado = new RepresentanteDto();
        when(representanteService.criarRepresentante(novoRepresentanteDto)).thenReturn(representanteCriado);

        // Act
        ResponseEntity<RepresentanteDto> response = representanteController.criarRepresentante(novoRepresentanteDto);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(representanteCriado, response.getBody());
        verify(representanteService, times(1)).criarRepresentante(novoRepresentanteDto);
    }

    @Test
    void listarRepresentantes_ComSucesso() {
        // Arrange
        List<RepresentanteDto> representantes = Arrays.asList(new RepresentanteDto(), new RepresentanteDto());
        when(representanteService.listarRepresentantes()).thenReturn(representantes);

        // Act
        ResponseEntity<List<RepresentanteDto>> response = representanteController.listarRepresentantes();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(representantes, response.getBody());
        verify(representanteService, times(1)).listarRepresentantes();
    }

    @Test
    void obterRepresentantePorId_ComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID representanteId = UUID.randomUUID();
        RepresentanteDto representanteDetalhado = new RepresentanteDto();
        when(representanteService.obterRepresentantePorId(representanteId)).thenReturn(representanteDetalhado);

        // Act
        ResponseEntity<RepresentanteDto> response = representanteController.obterRepresentantePorId(representanteId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(representanteDetalhado, response.getBody());
        verify(representanteService, times(1)).obterRepresentantePorId(representanteId);
    }

    @Test
    void atualizar_RepresentanteComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID representanteId = UUID.randomUUID();
        RepresentanteUpdateDto representanteAtualizadoDto = new RepresentanteUpdateDto();
        RepresentanteDto representanteAtualizado = new RepresentanteDto();
        when(representanteService.atualizarRepresentante(representanteId, representanteAtualizadoDto)).thenReturn(representanteAtualizado);

        // Act
        ResponseEntity<RepresentanteDto> response = representanteController.atualizarRepresentante(representanteId, representanteAtualizadoDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(representanteAtualizado, response.getBody());
        verify(representanteService, times(1)).atualizarRepresentante(representanteId, representanteAtualizadoDto);
    }

    @Test
    void deletar_RepresentanteComSucesso() {
        // Arrange
        UUID representanteId = UUID.randomUUID();

        // Act
        representanteController.deletarRepresentante(representanteId);

        // Assert
        verify(representanteService, times(1)).deletarRepresentante(representanteId);
    }
}
