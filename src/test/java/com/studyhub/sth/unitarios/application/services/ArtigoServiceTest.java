package com.studyhub.sth.unitarios.application.services;

import com.studyhub.sth.application.dtos.artigo.ArtigoCreateDto;
import com.studyhub.sth.application.dtos.artigo.ArtigoDto;
import com.studyhub.sth.application.dtos.tag.TagDto;
import com.studyhub.sth.domain.articles.Artigo;
import com.studyhub.sth.domain.before.entities.Tag;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.before.repositories.IArtigoRepository;
import com.studyhub.sth.domain.before.repositories.ITagRepository;
import com.studyhub.sth.domain.before.repositories.IUsuarioRepository;
import com.studyhub.sth.application.services.ArtigoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArtigoServiceTest {

    @Mock
    private IArtigoRepository artigoRepository;

    @Mock
    private ITagRepository tagRepository;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private ArtigoService artigoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarDeveSalvarEMapearArtigo() {
        ArtigoCreateDto artigoCreateDto = new ArtigoCreateDto();
        Artigo artigo = new Artigo();
        ArtigoDto artigoDto = new ArtigoDto();

        when(mapper.map(artigoCreateDto, Artigo.class)).thenReturn(artigo);
        when(artigoRepository.save(artigo)).thenReturn(artigo);
        when(mapper.map(artigo, ArtigoDto.class)).thenReturn(artigoDto);

        ArtigoDto resultado = artigoService.criar(artigoCreateDto);

        assertNotNull(resultado);
        assertEquals(artigoDto, resultado);
        verify(mapper).map(artigoCreateDto, Artigo.class);
        verify(artigoRepository).save(artigo);
        verify(mapper).map(artigo, ArtigoDto.class);
    }

    @Test
    void listarArtigosDeveRetornarListaDeArtigos() {
        List<Artigo> artigos = List.of(new Artigo(), new Artigo());
        List<ArtigoDto> artigosDto = List.of(new ArtigoDto(), new ArtigoDto());

        when(artigoRepository.findAll()).thenReturn(artigos);
        when(mapper.map(any(Artigo.class), eq(ArtigoDto.class)))
                .thenReturn(new ArtigoDto())
                .thenReturn(new ArtigoDto());

        List<ArtigoDto> resultado = artigoService.listarArtigos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(artigoRepository).findAll();
        verify(mapper, times(2)).map(any(Artigo.class), eq(ArtigoDto.class));
    }

    @Test
    void buscarArtigoPorIdDeveRetornarArtigoDto() {
        UUID id = UUID.randomUUID();
        Artigo artigo = new Artigo();
        ArtigoDto artigoDto = new ArtigoDto();

        when(artigoRepository.findById(id)).thenReturn(Optional.of(artigo));
        when(mapper.map(artigo, ArtigoDto.class)).thenReturn(artigoDto);

        ArtigoDto resultado = artigoService.buscarArtigoPorId(id);

        assertNotNull(resultado);
        assertEquals(artigoDto, resultado);
        verify(artigoRepository).findById(id);
        verify(mapper).map(artigo, ArtigoDto.class);
    }

    @Test
    void adicionarTagDeveAssociarTagsAoArtigo() {
        UUID artigoId = UUID.randomUUID();
        Artigo artigo = new Artigo();
        artigo.setTags(new ArrayList<>());
        Tag tag = new Tag();
        TagDto tagDto = new TagDto();
        tagDto.setId(UUID.randomUUID());

        when(artigoRepository.findById(artigoId)).thenReturn(Optional.of(artigo));
        when(tagRepository.findById(tagDto.getId())).thenReturn(Optional.of(tag));
        when(mapper.map(artigo, ArtigoDto.class)).thenReturn(new ArtigoDto());

        ArtigoDto resultado = artigoService.adicionarTag(artigoId, List.of(tagDto));

        assertNotNull(resultado);
        assertTrue(artigo.getTags().contains(tag));
        verify(artigoRepository).findById(artigoId);
        verify(tagRepository).findById(tagDto.getId());
        verify(artigoRepository).save(artigo);
        verify(mapper).map(artigo, ArtigoDto.class);
    }

    @Test
    void deletarDeveRemoverArtigo() {
        UUID id = UUID.randomUUID();
        Artigo artigo = new Artigo();

        when(artigoRepository.findById(id)).thenReturn(Optional.of(artigo));

        assertDoesNotThrow(() -> artigoService.deletar(id));
        verify(artigoRepository).findById(id);
        verify(artigoRepository).delete(artigo);
    }
}