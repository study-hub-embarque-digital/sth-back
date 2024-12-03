package com.studyhub.sth.unitarios.application.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoCreateDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoUpdateDto;
import com.studyhub.sth.application.services.InstituicaoEnsinoService;
import com.studyhub.sth.domain.entities.InstituicaoEnsino;
import com.studyhub.sth.domain.repositories.InstituicaoEnsinoRepository;
import com.studyhub.sth.libs.mapper.IMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

public class InstituicaoEnsinoServiceTest {

    @Mock
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private InstituicaoEnsinoService instituicaoEnsinoService;

    private InstituicaoEnsino instituicaoEnsino;
    private InstituicaoEnsinoDto instituicaoEnsinoDto;
    private UUID instituicaoId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        instituicaoId = UUID.randomUUID();
        instituicaoEnsino = new InstituicaoEnsino();
        instituicaoEnsino.setInstituicaoEnsinoId(instituicaoId);
        instituicaoEnsino.setNome("Test Instituicao");
        instituicaoEnsino.setCoordenador("Test Coordenador");
        instituicaoEnsino.setEndereco("Test Address");

        instituicaoEnsinoDto = new InstituicaoEnsinoDto();
        instituicaoEnsinoDto.setInstituicaoEnsinoId(instituicaoId);
        instituicaoEnsinoDto.setNome("Test Instituicao");
        instituicaoEnsinoDto.setCoordenador("Test Coordenador");
        instituicaoEnsinoDto.setEndereco("Test Address");
    }

    @Test
    public void testFindAll() {
        when(instituicaoEnsinoRepository.findAll()).thenReturn(List.of(instituicaoEnsino));
        when(mapper.map(instituicaoEnsino, InstituicaoEnsinoDto.class)).thenReturn(instituicaoEnsinoDto);

        var result = instituicaoEnsinoService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(instituicaoEnsinoDto.getNome(), result.get(0).getNome());
    }

    @Test
    public void testFindById() {
        when(instituicaoEnsinoRepository.findById(instituicaoId)).thenReturn(Optional.of(instituicaoEnsino));
        when(mapper.map(instituicaoEnsino, InstituicaoEnsinoDto.class)).thenReturn(instituicaoEnsinoDto);

        var result = instituicaoEnsinoService.findById(instituicaoId);
        assertNotNull(result);
        assertEquals(instituicaoEnsinoDto.getNome(), result.getNome());
    }

    @Test
    public void testFindByIdThrowsEntityNotFoundException() {
        when(instituicaoEnsinoRepository.findById(instituicaoId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> instituicaoEnsinoService.findById(instituicaoId));
    }

    @Test
    public void testSave() {
        InstituicaoEnsinoCreateDto createDto = new InstituicaoEnsinoCreateDto();
        createDto.setNome("New Instituicao");
        createDto.setCoordenador("New Coordenador");
        createDto.setEndereco("New Address");

        when(mapper.map(createDto, InstituicaoEnsino.class)).thenReturn(instituicaoEnsino);
        when(instituicaoEnsinoRepository.save(instituicaoEnsino)).thenReturn(instituicaoEnsino);
        when(mapper.map(instituicaoEnsino, InstituicaoEnsinoDto.class)).thenReturn(instituicaoEnsinoDto);

        var result = instituicaoEnsinoService.save(createDto);

        assertNotNull(result);
        assertEquals(instituicaoEnsinoDto.getNome(), result.getNome());
    }

    @Test
    public void testDelete() {
        when(instituicaoEnsinoRepository.findById(instituicaoId)).thenReturn(Optional.of(instituicaoEnsino));

        instituicaoEnsinoService.delete(instituicaoId);

        verify(instituicaoEnsinoRepository, times(1)).delete(instituicaoEnsino);
    }

    @Test
    public void testDeleteThrowsEntityNotFoundException() {
        when(instituicaoEnsinoRepository.findById(instituicaoId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> instituicaoEnsinoService.delete(instituicaoId));
    }

    @Test
    public void testUpdate() {
        InstituicaoEnsinoUpdateDto updateDto = new InstituicaoEnsinoUpdateDto();
        updateDto.setNome("Updated Instituicao");
        instituicaoEnsinoDto.setNome("Updated Instituicao");

        when(instituicaoEnsinoRepository.findById(instituicaoId)).thenReturn(Optional.of(instituicaoEnsino));
        when(instituicaoEnsinoRepository.save(instituicaoEnsino)).thenReturn(instituicaoEnsino);
        when(mapper.map(instituicaoEnsino, InstituicaoEnsinoDto.class)).thenReturn(instituicaoEnsinoDto);

        var result = instituicaoEnsinoService.update(instituicaoId, updateDto);
        assertNotNull(result);
        assertEquals("Updated Instituicao", result.getNome());
    }

    @Test
    public void testUpdateThrowsEntityNotFoundException() {
        InstituicaoEnsinoUpdateDto updateDto = new InstituicaoEnsinoUpdateDto();
        updateDto.setNome("Updated Instituicao");

        when(instituicaoEnsinoRepository.findById(instituicaoId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> instituicaoEnsinoService.update(instituicaoId, updateDto));
    }
}