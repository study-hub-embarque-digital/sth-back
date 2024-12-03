package com.studyhub.sth.integracao.api.controllers;
import com.studyhub.sth.api.controllers.DiscussaoController;
import com.studyhub.sth.application.dtos.discussao.DiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.NewDiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.UpdatedDiscussaoDto;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IDiscussaoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

public class DiscussaoControllerTest {
    @InjectMocks
    private DiscussaoController discussaoController;

    @Mock
    private IDiscussaoService discussaoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll_Sucesso() {
        // Arrange
        List<DiscussaoDto> discussaoList = Arrays.asList(new DiscussaoDto(), new DiscussaoDto());
        when(discussaoService.findAll()).thenReturn(discussaoList);

        // Act
        ResponseEntity<List<DiscussaoDto>> response = discussaoController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(discussaoList, response.getBody());
    }

    @Test
    public void testGetAllChild_Sucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID discussaoId = UUID.randomUUID();
        List<DiscussaoDto> childDiscussaoList = Arrays.asList(new DiscussaoDto(), new DiscussaoDto());
        when(discussaoService.findAllChild(discussaoId)).thenReturn(childDiscussaoList);

        // Act
        ResponseEntity<List<DiscussaoDto>> response = discussaoController.getAllChild(discussaoId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(childDiscussaoList, response.getBody());
    }

    @Test
    public void testGetAllChild_ElementoNaoEncontrado() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID discussaoId = UUID.randomUUID();
        when(discussaoService.findAllChild(discussaoId)).thenThrow(new ElementoNaoEncontradoExcecao("Discussão não encontrada"));

        // Act & Assert
        Exception exception = assertThrows(ElementoNaoEncontradoExcecao.class, () -> {
            discussaoController.getAllChild(discussaoId);
        });
        assertEquals("Discussão não encontrada", exception.getMessage());
    }

    @Test
    public void testCreate_Sucesso() {
        // Arrange
        NewDiscussaoDto newDiscussaoDto = new NewDiscussaoDto();
        DiscussaoDto createdDiscussaoDto = new DiscussaoDto();
        when(discussaoService.create(eq(newDiscussaoDto), any(Usuario.class))).thenReturn(createdDiscussaoDto);

        // Act
        ResponseEntity<DiscussaoDto> response = discussaoController.create(newDiscussaoDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdDiscussaoDto, response.getBody());
    }

    @Test
    public void testCreateChild_Sucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        NewDiscussaoDto newDiscussaoDto = new NewDiscussaoDto();
        UUID discussaoPaiId = UUID.randomUUID();
        Usuario usuario = new Usuario();
        DiscussaoDto createdDiscussaoDto = new DiscussaoDto();
        when(discussaoService.createChild(newDiscussaoDto, discussaoPaiId, usuario)).thenReturn(createdDiscussaoDto);

        // Act
        ResponseEntity<DiscussaoDto> response = discussaoController.createChild(newDiscussaoDto, discussaoPaiId, usuario);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdDiscussaoDto, response.getBody());
    }

    @Test
    public void testUpdate_Sucesso() throws Exception {
        // Arrange
        UUID discussaoId = UUID.randomUUID();
        UpdatedDiscussaoDto updatedDiscussaoDto = new UpdatedDiscussaoDto();
        Usuario usuario = new Usuario();
        DiscussaoDto updatedDiscussao = new DiscussaoDto();
        when(discussaoService.update(discussaoId, updatedDiscussaoDto, usuario)).thenReturn(updatedDiscussao);

        // Act
        ResponseEntity<DiscussaoDto> response = discussaoController.update(updatedDiscussaoDto, discussaoId, usuario);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDiscussao, response.getBody());
    }
}
