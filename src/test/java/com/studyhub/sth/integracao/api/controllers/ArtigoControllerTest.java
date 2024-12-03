package com.studyhub.sth.integracao.api.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.studyhub.sth.api.controllers.ArtigoController;
import com.studyhub.sth.application.dtos.artigo.ArtigoCreateDto;
import com.studyhub.sth.application.dtos.artigo.ArtigoDto;
import com.studyhub.sth.application.dtos.artigo.ArtigoUpdateDto;
import com.studyhub.sth.application.dtos.tag.TagDto;
import com.studyhub.sth.domain.services.IArtigoService;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ArtigoControllerTest {

    @InjectMocks
    private ArtigoController artigoController;

    @Mock
    private IArtigoService artigoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void testListarArtigos_Sucesso() {
        // Arrange
        List<ArtigoDto> artigos = Arrays.asList(new ArtigoDto(), new ArtigoDto());
        when(artigoService.listarArtigos()).thenReturn(artigos);

        // Act
        List<ArtigoDto> result = artigoController.listarArtigos();

        // Assert
        assertEquals(artigos.size(), result.size());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testBuscarArtigoPorId_Sucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        ArtigoDto artigoDto = new ArtigoDto();
        when(artigoService.buscarArtigoPorId(id)).thenReturn(artigoDto);

        // Act
        ResponseEntity<ArtigoDto> response = artigoController.buscarArtigoPoId(id);

        // Assert
        assertEquals(artigoDto, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testBuscarArtigoPorTitulo_Sucesso() {
        // Arrange
        String titulo = "Título";
        List<ArtigoDto> artigos = Arrays.asList(new ArtigoDto(), new ArtigoDto());
        when(artigoService.buscarArtigoPorTitulo(titulo)).thenReturn(artigos);

        // Act
        ResponseEntity<List<ArtigoDto>> response = artigoController.buscarArtigoPorTitulo(titulo);

        // Assert
        assertEquals(artigos, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testCriarArtigo_Sucesso() {
        // Arrange
        ArtigoCreateDto dto = new ArtigoCreateDto();
        ArtigoDto artigoDto = new ArtigoDto();
        when(artigoService.criar(dto)).thenReturn(artigoDto);

        // Configurar o contexto da requisição
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Act
        ResponseEntity<ArtigoDto> response = artigoController.criarArtigo(dto);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(artigoDto, response.getBody());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testAtualizarArtigo_Sucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        ArtigoUpdateDto dto = new ArtigoUpdateDto();
        ArtigoDto artigoDto = new ArtigoDto();
        when(artigoService.atualizar(id, dto)).thenReturn(artigoDto);

        // Act
        ResponseEntity<ArtigoDto> response = artigoController.atualizarArtigo(id, dto);

        // Assert
        assertEquals(artigoDto, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testAdicionarTags_Sucesso() {
        // Arrange
        UUID artigoId = UUID.randomUUID();
        List<TagDto> tags = Arrays.asList(new TagDto(), new TagDto());
        ArtigoDto artigoDto = new ArtigoDto();
        when(artigoService.adicionarTag(artigoId, tags)).thenReturn(artigoDto);

        // Act
        ResponseEntity<ArtigoDto> response = artigoController.adicionarTags(artigoId, tags);

        // Assert
        assertEquals(artigoDto, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testRemoverTag_Sucesso() {
        // Arrange
        UUID artigoId = UUID.randomUUID();
        UUID tagId = UUID.randomUUID();
        ArtigoDto artigoDto = new ArtigoDto();
        when(artigoService.removerTag(artigoId, tagId)).thenReturn(artigoDto);

        // Act
        ResponseEntity<ArtigoDto> response = artigoController.removerTag(artigoId, tagId);

        // Assert
        assertEquals(artigoDto, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testDeletarArtigo_Sucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(artigoService).deletar(id);

        // Act
        @SuppressWarnings("rawtypes")
        ResponseEntity response = artigoController.deletarArtigo(id);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(artigoService, times(1)).deletar(id);
    }
}
