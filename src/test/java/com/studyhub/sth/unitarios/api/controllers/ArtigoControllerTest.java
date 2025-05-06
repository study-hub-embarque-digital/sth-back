package com.studyhub.sth.unitarios.api.controllers;

import com.studyhub.sth.api.controllers.ArtigoController;
import com.studyhub.sth.application.dtos.artigo.ArtigoDto;
import com.studyhub.sth.application.dtos.artigo.ArtigoUpdateDto;
import com.studyhub.sth.application.dtos.tag.TagDto;
import com.studyhub.sth.domain.before.services.IArtigoService;
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

class ArtigoControllerTest {

    @Mock
    private IArtigoService artigoService;

    @InjectMocks
    private ArtigoController artigoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarArtigos_ComSucesso() {
        // Arrange
        List<ArtigoDto> artigos = Arrays.asList(new ArtigoDto(), new ArtigoDto());
        when(artigoService.listarArtigos()).thenReturn(artigos);

        // Act
        List<ArtigoDto> response = artigoController.listarArtigos();

        // Assert
        assertEquals(artigos, response);
        verify(artigoService, times(1)).listarArtigos();
    }

    @Test
    void buscarArtigoPorId_ComSucesso() {
        // Arrange
        UUID artigoId = UUID.randomUUID();
        ArtigoDto artigo = new ArtigoDto();
        when(artigoService.buscarArtigoPorId(artigoId)).thenReturn(artigo);

        // Act
        ResponseEntity<ArtigoDto> response = artigoController.buscarArtigoPoId(artigoId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(artigo, response.getBody());
        verify(artigoService, times(1)).buscarArtigoPorId(artigoId);
    }

    @Test
    void buscarArtigoPorTitulo_ComSucesso() {
        // Arrange
        String titulo = "Título de Teste";
        List<ArtigoDto> artigos = Arrays.asList(new ArtigoDto(), new ArtigoDto());
        when(artigoService.buscarArtigoPorTitulo(titulo)).thenReturn(artigos);

        // Act
        ResponseEntity<List<ArtigoDto>> response = artigoController.buscarArtigoPorTitulo(titulo);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(artigos, response.getBody());
        verify(artigoService, times(1)).buscarArtigoPorTitulo(titulo);
    }

    @Test
    void buscarArtigoPorUsuario_ComSucesso() {
        // Arrange
        UUID autorId = UUID.randomUUID();
        List<ArtigoDto> artigos = Arrays.asList(new ArtigoDto(), new ArtigoDto());
        when(artigoService.buscarArtigoPorAutor(autorId)).thenReturn(artigos);

        // Act
        ResponseEntity<List<ArtigoDto>> response = artigoController.buscarArtigoPorUsuario(autorId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(artigos, response.getBody());
        verify(artigoService, times(1)).buscarArtigoPorAutor(autorId);
    }

    @Test
    void buscarArtigoPorTag_ComSucesso() {
        // Arrange
        UUID tagId = UUID.randomUUID();
        List<ArtigoDto> artigos = Arrays.asList(new ArtigoDto(), new ArtigoDto());
        when(artigoService.buscarArtigoPorTag(tagId)).thenReturn(artigos);

        // Act
        ResponseEntity<List<ArtigoDto>> response = artigoController.buscarArtigoPorTag(tagId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(artigos, response.getBody());
        verify(artigoService, times(1)).buscarArtigoPorTag(tagId);
    }

    @Test
    void atualizarArtigo_ComSucesso() {
        // Arrange
        UUID artigoId = UUID.randomUUID();
        ArtigoUpdateDto artigoUpdateDto = new ArtigoUpdateDto();
        ArtigoDto artigoAtualizado = new ArtigoDto();
        when(artigoService.atualizar(artigoId, artigoUpdateDto)).thenReturn(artigoAtualizado);

        // Act
        ResponseEntity<ArtigoDto> response = artigoController.atualizarArtigo(artigoId, artigoUpdateDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(artigoAtualizado, response.getBody());
        verify(artigoService, times(1)).atualizar(artigoId, artigoUpdateDto);
    }

    @Test
    void adicionarTags_ComSucesso() {
        // Arrange
        UUID artigoId = UUID.randomUUID();
        List<TagDto> tags = Arrays.asList(new TagDto(), new TagDto());
        ArtigoDto artigoComTags = new ArtigoDto();
        when(artigoService.adicionarTag(artigoId, tags)).thenReturn(artigoComTags);

        // Act
        ResponseEntity<ArtigoDto> response = artigoController.adicionarTags(artigoId, tags);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(artigoComTags, response.getBody());
        verify(artigoService, times(1)).adicionarTag(artigoId, tags);
    }

    @Test
    void removerTag_ComSucesso() {
        // Arrange
        UUID artigoId = UUID.randomUUID();
        UUID tagId = UUID.randomUUID();
        ArtigoDto artigoSemTag = new ArtigoDto();
        when(artigoService.removerTag(artigoId, tagId)).thenReturn(artigoSemTag);

        // Act
        ResponseEntity<ArtigoDto> response = artigoController.removerTag(artigoId, tagId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(artigoSemTag, response.getBody());
        verify(artigoService, times(1)).removerTag(artigoId, tagId);
    }

    @Test
    void deletarArtigo_ComSucesso() {
        // Arrange
        UUID artigoId = UUID.randomUUID();

        // Act
        ResponseEntity<Void> response = artigoController.deletarArtigo(artigoId);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(artigoService, times(1)).deletar(artigoId);
    }
}
