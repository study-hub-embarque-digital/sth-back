package com.studyhub.sth.unitarios.application.services;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.studyhub.sth.application.dtos.empresas.EmpresaCreateDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaUpdateDto;
import com.studyhub.sth.application.services.EmpresaService;
import com.studyhub.sth.domain.before.entities.Empresa;
import com.studyhub.sth.domain.before.repositories.IEmpresaRepository;
import com.studyhub.sth.libs.mapper.IMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class EmpresaServiceTest {

    @Mock
    private IEmpresaRepository empresaRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private EmpresaService empresaService;

    private Empresa empresa;
    private EmpresaDto empresaDto;
    private UUID empresaId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        empresaId = UUID.randomUUID();
        empresa = new Empresa();
        empresa.setEmpresaId(empresaId);
        empresa.setNomeFantasia("Test Empresa");
        empresa.setEmail("test@empresa.com");
        empresa.setTelefone("123456789");

        empresaDto = new EmpresaDto();
        empresaDto.setEmpresaId(empresaId);
        empresaDto.setNomeFantasia("Test Empresa");
        empresaDto.setEmail("test@empresa.com");
        empresaDto.setTelefone("123456789");
    }

    @Test
    public void testFindAll() {
        when(empresaRepository.findAll()).thenReturn(List.of(empresa));
        when(mapper.map(empresa, EmpresaDto.class)).thenReturn(empresaDto);

        var result = empresaService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(empresaDto.getNomeFantasia(), result.get(0).getNomeFantasia());
    }

    @Test
    public void testFindById() {
        when(empresaRepository.findById(empresaId)).thenReturn(Optional.of(empresa));
        when(mapper.map(empresa, EmpresaDto.class)).thenReturn(empresaDto);

        var result = empresaService.findById(empresaId);
        assertNotNull(result);
        assertEquals(empresaDto.getNomeFantasia(), result.getNomeFantasia());
    }

    @Test
    public void testFindByIdThrowsEntityNotFoundException() {
        when(empresaRepository.findById(empresaId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> empresaService.findById(empresaId));
    }

    @Test
    public void testSave() {
        EmpresaCreateDto createDto = new EmpresaCreateDto();
        createDto.setNomeFantasia("New Empresa");
        createDto.setEmail("new@empresa.com");
        createDto.setTelefone("987654321");

        when(mapper.map(createDto, Empresa.class)).thenReturn(empresa);
        when(empresaRepository.save(empresa)).thenReturn(empresa);
        when(mapper.map(empresa, EmpresaDto.class)).thenReturn(empresaDto);

        var result = empresaService.save(createDto);

        assertNotNull(result);
        assertEquals(empresaDto.getNomeFantasia(), result.getNomeFantasia());
    }

    @Test
    public void testUpdate() {
        EmpresaUpdateDto updateDto = new EmpresaUpdateDto();
        updateDto.setNomeFantasia("Updated Empresa");
        Empresa eAtualizada = empresa;
        eAtualizada.setNomeFantasia(updateDto.getNomeFantasia());
        EmpresaDto eDtoAtualizado = empresaDto;
        eDtoAtualizado.setNomeFantasia(eAtualizada.getNomeFantasia());

        when(empresaRepository.findById(empresaId)).thenReturn(Optional.of(empresa));
        when(empresaRepository.save(eAtualizada)).thenReturn(eAtualizada);
        when(mapper.map(eAtualizada, EmpresaDto.class)).thenReturn(eDtoAtualizado);

        var result = empresaService.update(empresaId, updateDto);
        assertNotNull(result);
        assertEquals("Updated Empresa", result.getNomeFantasia());
    }

    @Test
    public void testUpdateThrowsEntityNotFoundException() {
        EmpresaUpdateDto updateDto = new EmpresaUpdateDto();
        updateDto.setNomeFantasia("Updated Empresa");

        when(empresaRepository.findById(empresaId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> empresaService.update(empresaId, updateDto));
    }

    @Test
    public void testDelete() {
        when(empresaRepository.findById(empresaId)).thenReturn(Optional.of(empresa));

        empresaService.delete(empresaId);

        verify(empresaRepository, times(1)).deleteById(empresaId);
    }

    @Test
    public void testDeleteThrowsEntityNotFoundException() {
        when(empresaRepository.findById(empresaId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> empresaService.delete(empresaId));
    }
}