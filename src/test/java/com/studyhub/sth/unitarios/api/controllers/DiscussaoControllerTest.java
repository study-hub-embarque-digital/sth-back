package com.studyhub.sth.unitarios.api.controllers;

import com.studyhub.sth.api.controllers.DiscussaoController;
import com.studyhub.sth.application.dtos.discussao.DiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.NewDiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.UpdatedDiscussaoDto;
import com.studyhub.sth.domain.before.entities.Usuario;
import com.studyhub.sth.domain.before.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.before.services.IDiscussaoService;
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

class DiscussaoControllerTest {

    @Mock
    private IDiscussaoService discussaoService;

    @InjectMocks
    private DiscussaoController discussaoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ComSucesso() {
        // Arrange
        List<DiscussaoDto> discussoes = Arrays.asList(new DiscussaoDto(), new DiscussaoDto());
        when(discussaoService.findAll()).thenReturn(discussoes);

        // Act
        ResponseEntity<List<DiscussaoDto>> response = discussaoController.getAll();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(discussoes, response.getBody());
        verify(discussaoService, times(1)).findAll();
    }

    @Test
    void getAllChild_ComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID discussaoId = UUID.randomUUID();
        List<DiscussaoDto> childDiscussoes = Arrays.asList(new DiscussaoDto(), new DiscussaoDto());
        when(discussaoService.findAllChild(discussaoId)).thenReturn(childDiscussoes);

        // Act
        ResponseEntity<List<DiscussaoDto>> response = discussaoController.getAllChild(discussaoId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(childDiscussoes, response.getBody());
        verify(discussaoService, times(1)).findAllChild(discussaoId);
    }

    @Test
    void create_ComSucesso() {
        // Arrange
        NewDiscussaoDto newDiscussaoDto = new NewDiscussaoDto();
        DiscussaoDto discussaoCriada = new DiscussaoDto();
        when(discussaoService.create(newDiscussaoDto, new Usuario())).thenReturn(discussaoCriada);

        // Act
        ResponseEntity<DiscussaoDto> response = discussaoController.create(newDiscussaoDto);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(discussaoCriada, response.getBody());
        verify(discussaoService, times(1)).create(newDiscussaoDto, new Usuario());
    }

    @Test
    void createChild_ComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID discussaoPaiId = UUID.randomUUID();
        NewDiscussaoDto newDiscussaoDto = new NewDiscussaoDto();
        Usuario usuario = new Usuario();
        DiscussaoDto discussaoCriada = new DiscussaoDto();
        when(discussaoService.createChild(newDiscussaoDto, discussaoPaiId, usuario)).thenReturn(discussaoCriada);

        // Act
        ResponseEntity<DiscussaoDto> response = discussaoController.createChild(newDiscussaoDto, discussaoPaiId, usuario);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(discussaoCriada, response.getBody());
        verify(discussaoService, times(1)).createChild(newDiscussaoDto, discussaoPaiId, usuario);
    }

    @Test
    void update_ComSucesso() throws Exception {
        // Arrange
        UUID discussaoId = UUID.randomUUID();
        UpdatedDiscussaoDto updatedDiscussaoDto = new UpdatedDiscussaoDto();
        Usuario usuario = new Usuario();
        DiscussaoDto discussaoAtualizada = new DiscussaoDto();
        when(discussaoService.update(discussaoId, updatedDiscussaoDto, usuario)).thenReturn(discussaoAtualizada);

        // Act
        ResponseEntity<DiscussaoDto> response = discussaoController.update(updatedDiscussaoDto, discussaoId, usuario);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(discussaoAtualizada, response.getBody());
        verify(discussaoService, times(1)).update(discussaoId, updatedDiscussaoDto, usuario);
    }
}
