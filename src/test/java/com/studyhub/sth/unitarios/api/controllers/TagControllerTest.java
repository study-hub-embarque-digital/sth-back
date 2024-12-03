package com.studyhub.sth.unitarios.api.controllers;

import com.studyhub.sth.api.controllers.TagController;
import com.studyhub.sth.application.dtos.tag.TagCreateAndUpdateDTO;
import com.studyhub.sth.application.dtos.tag.TagDto;
import com.studyhub.sth.domain.services.ITagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TagControllerTest {

    @Mock
    private ITagService tagService;

    @InjectMocks
    private TagController tagController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTags_ComSucesso() {
        // Arrange
        List<TagDto> tags = Arrays.asList(new TagDto(), new TagDto());
        when(tagService.listar()).thenReturn(tags);

        // Act
        ResponseEntity<List<TagDto>> response = tagController.listarTags();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tags, response.getBody());
        verify(tagService, times(1)).listar();
    }

    @Test
    void buscarTagPorId_ComSucesso() {
        // Arrange
        UUID tagId = UUID.randomUUID();
        TagDto tagEncontrada = new TagDto();
        when(tagService.buscarPorId(tagId)).thenReturn(tagEncontrada);

        // Act
        ResponseEntity<TagDto> response = tagController.buscarTagPorId(tagId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tagEncontrada, response.getBody());
        verify(tagService, times(1)).buscarPorId(tagId);
    }

    @Test
    void buscarTagPorNome_ComSucesso() {
        // Arrange
        String nomeTag = "TagTeste";
        TagDto tagEncontrada = new TagDto();
        when(tagService.buscarPorNome(nomeTag)).thenReturn(tagEncontrada);

        // Act
        ResponseEntity<TagDto> response = tagController.buscarTagPorNome(nomeTag);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tagEncontrada, response.getBody());
        verify(tagService, times(1)).buscarPorNome(nomeTag);
    }

    @Test
    void atualizarTag_ComSucesso() {
        // Arrange
        UUID tagId = UUID.randomUUID();
        TagCreateAndUpdateDTO tagUpdateDto = new TagCreateAndUpdateDTO();
        TagDto tagAtualizada = new TagDto();
        when(tagService.atualizar(tagId, tagUpdateDto)).thenReturn(tagAtualizada);

        // Act
        ResponseEntity<TagDto> response = tagController.atualizarTag(tagUpdateDto, tagId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tagAtualizada, response.getBody());
        verify(tagService, times(1)).atualizar(tagId, tagUpdateDto);
    }

    @Test
    void deletarTag_ComSucesso() {
        // Arrange
        UUID tagId = UUID.randomUUID();

        // Act
        ResponseEntity<Void> response = tagController.deletarTag(tagId);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(tagService, times(1)).deletar(tagId);
    }
}
