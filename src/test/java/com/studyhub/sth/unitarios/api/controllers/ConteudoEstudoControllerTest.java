package com.studyhub.sth.unitarios.api.controllers;

import com.studyhub.sth.api.controllers.ConteudoEstudoController;
import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoCreateDto;
import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoDto;
import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoUpdateDto;
import com.studyhub.sth.application.services.ConteudoEstudoService;
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

class ConteudoEstudoControllerTest {

    @Mock
    private ConteudoEstudoService conteudoEstudoService;

    @InjectMocks
    private ConteudoEstudoController conteudoEstudoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarConteudoEstudo_ComSucesso() {
        // Arrange
        ConteudoEstudoCreateDto createDto = new ConteudoEstudoCreateDto();
        ConteudoEstudoCreateDto retornoDto = new ConteudoEstudoCreateDto();
        when(conteudoEstudoService.criarConteudoEstudo(createDto)).thenReturn(retornoDto);

        // Act
        ResponseEntity<ConteudoEstudoCreateDto> response = conteudoEstudoController.criarConteudoEstudo(createDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(retornoDto, response.getBody());
        verify(conteudoEstudoService, times(1)).criarConteudoEstudo(createDto);
    }

    @Test
    void listarConteudosEstudo_ComSucesso() {
        // Arrange
        List<ConteudoEstudoCreateDto> conteudos = Arrays.asList(new ConteudoEstudoCreateDto(), new ConteudoEstudoCreateDto());
        when(conteudoEstudoService.listarConteudosEstudo()).thenReturn(conteudos);

        // Act
        ResponseEntity<List<ConteudoEstudoCreateDto>> response = conteudoEstudoController.listarConteudosEstudo();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(conteudos, response.getBody());
        verify(conteudoEstudoService, times(1)).listarConteudosEstudo();
    }

    @Test
    void obterConteudoEstudoPorId_ComSucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        ConteudoEstudoDto conteudo = new ConteudoEstudoDto();
        when(conteudoEstudoService.obterConteudoEstudoPorId(id)).thenReturn(conteudo);

        // Act
        ResponseEntity<ConteudoEstudoDto> response = conteudoEstudoController.obterConteudoEstudoPorId(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(conteudo, response.getBody());
        verify(conteudoEstudoService, times(1)).obterConteudoEstudoPorId(id);
    }

    @Test
    void atualizarConteudoEstudo_ComSucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        ConteudoEstudoUpdateDto updateDto = new ConteudoEstudoUpdateDto();
        ConteudoEstudoDto atualizado = new ConteudoEstudoDto();
        when(conteudoEstudoService.atualizarConteudoEstudo(id, updateDto)).thenReturn(atualizado);

        // Act
        ResponseEntity<ConteudoEstudoDto> response = conteudoEstudoController.atualizarConteudoEstudo(id, updateDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(atualizado, response.getBody());
        verify(conteudoEstudoService, times(1)).atualizarConteudoEstudo(id, updateDto);
    }

    @Test
    void deletarConteudoEstudo_ComSucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(conteudoEstudoService).deletarConteudoEstudo(id);

        // Act
        conteudoEstudoController.deletarConteudoEstudo(id);

        // Assert
        verify(conteudoEstudoService, times(1)).deletarConteudoEstudo(id);
    }
}
