package com.studyhub.sth.integracao.api.controllers;

import com.studyhub.sth.api.controllers.InstituicaoEnsinoController;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoCreateDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoUpdateDto;
import com.studyhub.sth.application.services.InstituicaoEnsinoService;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InstituicaoEnsinoControllerTest {
    @InjectMocks
    private InstituicaoEnsinoController instituicaoEnsinoController;

    @Mock
    private InstituicaoEnsinoService instituicaoEnsinoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetById_Sucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        InstituicaoEnsinoDto instituicaoDto = new InstituicaoEnsinoDto();
        when(instituicaoEnsinoService.findById(id)).thenReturn(instituicaoDto);

        // Act
        ResponseEntity<InstituicaoEnsinoDto> response = instituicaoEnsinoController.getById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(instituicaoDto, response.getBody());
    }

    @Test
    public void testGetById_NaoEncontrado() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(instituicaoEnsinoService.findById(id)).thenReturn(null);

        // Act
        ResponseEntity<InstituicaoEnsinoDto> response = instituicaoEnsinoController.getById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreate_Sucesso() {
        // Arrange
        InstituicaoEnsinoCreateDto createDto = new InstituicaoEnsinoCreateDto();
        InstituicaoEnsinoDto createdDto = new InstituicaoEnsinoDto();
        when(instituicaoEnsinoService.save(createDto)).thenReturn(createdDto);

        // Act
        ResponseEntity<InstituicaoEnsinoDto> response = instituicaoEnsinoController.create(createDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdDto, response.getBody());
    }

    @Test
    public void testUpdate_Sucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        InstituicaoEnsinoUpdateDto updateDto = new InstituicaoEnsinoUpdateDto();
        InstituicaoEnsinoDto updatedDto = new InstituicaoEnsinoDto();
        when(instituicaoEnsinoService.update(id, updateDto)).thenReturn(updatedDto);

        // Act
        ResponseEntity<InstituicaoEnsinoDto> response = instituicaoEnsinoController.update(id, updateDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDto, response.getBody());
    }

    @Test
    public void testDelete_Sucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(instituicaoEnsinoService).delete(id);

        // Act
        ResponseEntity<Void> response = instituicaoEnsinoController.delete(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(instituicaoEnsinoService, times(1)).delete(id);
    }
}
