package com.studyhub.sth.unitarios.api.controllers;

import com.studyhub.sth.api.controllers.InstituicaoEnsinoController;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoCreateDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoUpdateDto;
import com.studyhub.sth.application.services.InstituicaoEnsinoService;
import com.studyhub.sth.domain.entities.InstituicaoEnsino;
import com.studyhub.sth.domain.repositories.InstituicaoEnsinoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class InstituicaoEnsinoControllerTest {

    @Mock
    private InstituicaoEnsinoService instituicaoEnsinoService;

    @Mock
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    @InjectMocks
    private InstituicaoEnsinoController instituicaoEnsinoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_Success() {
        // Arrange
        List<InstituicaoEnsinoDto> instituicoes = Arrays.asList(new InstituicaoEnsinoDto(), new InstituicaoEnsinoDto());
        when(instituicaoEnsinoService.findAll()).thenReturn(instituicoes);

        // Act
        List<InstituicaoEnsinoDto> response = instituicaoEnsinoController.getAll();

        // Assert
        assertEquals(instituicoes, response);
        verify(instituicaoEnsinoService, times(1)).findAll();
    }

    @Test
    void getById_Found() {
        // Arrange
        UUID id = UUID.randomUUID();
        InstituicaoEnsinoDto instituicao = new InstituicaoEnsinoDto();
        when(instituicaoEnsinoService.findById(id)).thenReturn(instituicao);

        // Act
        ResponseEntity<InstituicaoEnsinoDto> response = instituicaoEnsinoController.getById(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(instituicao, response.getBody());
        verify(instituicaoEnsinoService, times(1)).findById(id);
    }

    @Test
    void getById_NotFound() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(instituicaoEnsinoService.findById(id)).thenReturn(null);

        // Act
        ResponseEntity<InstituicaoEnsinoDto> response = instituicaoEnsinoController.getById(id);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        verify(instituicaoEnsinoService, times(1)).findById(id);
    }

    @Test
    void create_Success() {
        // Arrange
        InstituicaoEnsinoCreateDto novaInstituicao = new InstituicaoEnsinoCreateDto();
        InstituicaoEnsinoDto instituicaoCriada = new InstituicaoEnsinoDto();
        when(instituicaoEnsinoService.save(novaInstituicao)).thenReturn(instituicaoCriada);

        // Act
        ResponseEntity<InstituicaoEnsinoDto> response = instituicaoEnsinoController.create(novaInstituicao);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(instituicaoCriada, response.getBody());
        verify(instituicaoEnsinoService, times(1)).save(novaInstituicao);
    }

    @Test
    void delete_Success() {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(instituicaoEnsinoService).delete(id);

        // Act
        ResponseEntity<Void> response = instituicaoEnsinoController.delete(id);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(instituicaoEnsinoService, times(1)).delete(id);
    }

    @Test
    void update_Success() {
        // Arrange
        UUID id = UUID.randomUUID();
        InstituicaoEnsinoUpdateDto instituicaoAtualizadaDto = new InstituicaoEnsinoUpdateDto();
        InstituicaoEnsinoDto instituicaoAtualizada = new InstituicaoEnsinoDto();
        when(instituicaoEnsinoService.update(id, instituicaoAtualizadaDto)).thenReturn(instituicaoAtualizada);

        // Act
        ResponseEntity<InstituicaoEnsinoDto> response = instituicaoEnsinoController.update(id, instituicaoAtualizadaDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(instituicaoAtualizada, response.getBody());
        verify(instituicaoEnsinoService, times(1)).update(id, instituicaoAtualizadaDto);
    }

    @Test
    void buscarPorNome_Success() {
        // Arrange
        String nome = "Faculdade";
        List<InstituicaoEnsino> instituicoes = Arrays.asList(new InstituicaoEnsino(), new InstituicaoEnsino());
        when(instituicaoEnsinoRepository.findByNomeContaining(nome)).thenReturn(instituicoes);

        // Act
        ResponseEntity<List<InstituicaoEnsino>> response = instituicaoEnsinoController.buscarPorNome(nome);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(instituicoes, response.getBody());
        verify(instituicaoEnsinoRepository, times(1)).findByNomeContaining(nome);
    }
}
