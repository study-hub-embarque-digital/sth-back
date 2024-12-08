package com.studyhub.sth.integracao.api.controllers;

import com.studyhub.sth.api.controllers.ConteudoEstudoController;
import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoCreateDto;
import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoDto;
import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoUpdateDto;
import com.studyhub.sth.application.services.ConteudoEstudoService;
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

public class ConteudoEstudoControllerTest {
    @InjectMocks
    private ConteudoEstudoController conteudoEstudoController;

    @Mock
    private ConteudoEstudoService conteudoEstudoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarConteudoEstudo_Sucesso() {
        // Arrange
        ConteudoEstudoCreateDto createDto = new ConteudoEstudoCreateDto();
        ConteudoEstudoCreateDto expectedResponse = new ConteudoEstudoCreateDto();
        when(conteudoEstudoService.criarConteudoEstudo(createDto)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ConteudoEstudoCreateDto> response = conteudoEstudoController.criarConteudoEstudo(createDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testListarConteudosEstudo_Sucesso() {
        // Arrange
        List<ConteudoEstudoCreateDto> conteudos = Arrays.asList(new ConteudoEstudoCreateDto(), new ConteudoEstudoCreateDto());
        when(conteudoEstudoService.listarConteudosEstudo()).thenReturn(conteudos);

        // Act
        ResponseEntity<List<ConteudoEstudoCreateDto>> response = conteudoEstudoController.listarConteudosEstudo();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(conteudos, response.getBody());
    }

    @Test
    public void testObterConteudoEstudoPorId_Sucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        ConteudoEstudoDto conteudoDto = new ConteudoEstudoDto();
        when(conteudoEstudoService.obterConteudoEstudoPorId(id)).thenReturn(conteudoDto);

        // Act
        ResponseEntity<ConteudoEstudoDto> response = conteudoEstudoController.obterConteudoEstudoPorId(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(conteudoDto, response.getBody());
    }

    @Test
    public void testAtualizarConteudoEstudo_Sucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        ConteudoEstudoUpdateDto updateDto = new ConteudoEstudoUpdateDto();
        ConteudoEstudoDto conteudoDto = new ConteudoEstudoDto();
        when(conteudoEstudoService.atualizarConteudoEstudo(id, updateDto)).thenReturn(conteudoDto);

        // Act
        ResponseEntity<ConteudoEstudoDto> response = conteudoEstudoController.atualizarConteudoEstudo(id, updateDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(conteudoDto, response.getBody());
    }

    @Test
    public void testDeletarConteudoEstudo_Sucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(conteudoEstudoService).deletarConteudoEstudo(id);

        // Act
        conteudoEstudoController.deletarConteudoEstudo(id);

        // Assert
        verify(conteudoEstudoService, times(1)).deletarConteudoEstudo(id);
    }
}
