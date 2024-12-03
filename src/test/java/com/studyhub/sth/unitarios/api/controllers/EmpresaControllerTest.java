package com.studyhub.sth.unitarios.api.controllers;

import com.studyhub.sth.api.controllers.EmpresaController;
import com.studyhub.sth.application.dtos.empresas.EmpresaCreateDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaUpdateDto;
import com.studyhub.sth.application.services.EmpresaService;
import com.studyhub.sth.domain.entities.Empresa;
import com.studyhub.sth.domain.repositories.IEmpresaRepository;
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

class EmpresaControllerTest {

    @Mock
    private EmpresaService empresaService;

    @Mock
    private IEmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaController empresaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmpresas_ComSucesso() {
        // Arrange
        List<EmpresaDto> empresas = Arrays.asList(new EmpresaDto(), new EmpresaDto());
        when(empresaService.findAll()).thenReturn(empresas);

        // Act
        ResponseEntity<List<EmpresaDto>> response = empresaController.getAllEmpresas();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(empresas, response.getBody());
        verify(empresaService, times(1)).findAll();
    }

    @Test
    void getEmpresaById_ComSucesso() {
        // Arrange
        UUID empresaId = UUID.randomUUID();
        EmpresaDto empresa = new EmpresaDto();
        when(empresaService.findById(empresaId)).thenReturn(empresa);

        // Act
        ResponseEntity<EmpresaDto> response = empresaController.getEmpresaById(empresaId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(empresa, response.getBody());
        verify(empresaService, times(1)).findById(empresaId);
    }

    @Test
    void updateEmpresa_ComSucesso() {
        // Arrange
        UUID empresaId = UUID.randomUUID();
        EmpresaUpdateDto empresaUpdateDto = new EmpresaUpdateDto();
        EmpresaDto empresaAtualizada = new EmpresaDto();
        when(empresaService.update(empresaId, empresaUpdateDto)).thenReturn(empresaAtualizada);

        // Act
        ResponseEntity<EmpresaDto> response = empresaController.updateEmpresa(empresaId, empresaUpdateDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(empresaAtualizada, response.getBody());
        verify(empresaService, times(1)).update(empresaId, empresaUpdateDto);
    }

    @Test
    void createEmpresa_ComSucesso() {
        // Arrange
        EmpresaCreateDto novaEmpresaDto = new EmpresaCreateDto();
        EmpresaDto empresaCriada = new EmpresaDto();
        when(empresaService.save(novaEmpresaDto)).thenReturn(empresaCriada);

        // Act
        ResponseEntity<EmpresaDto> response = empresaController.createEmpresa(novaEmpresaDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(empresaCriada, response.getBody());
        verify(empresaService, times(1)).save(novaEmpresaDto);
    }

    @Test
    void deleteEmpresa_ComSucesso() {
        // Arrange
        UUID empresaId = UUID.randomUUID();
        doNothing().when(empresaService).delete(empresaId);

        // Act
        ResponseEntity<Void> response = empresaController.deleteEmpresa(empresaId);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(empresaService, times(1)).delete(empresaId);
    }

    @Test
    void buscarPorNomeFantasia_ComSucesso() {
        // Arrange
        String nomeFantasia = "Tech";
        List<Empresa> empresas = Arrays.asList(new Empresa(), new Empresa());
        when(empresaRepository.findByNomeFantasiaContaining(nomeFantasia)).thenReturn(empresas);

        // Act
        ResponseEntity<List<Empresa>> response = empresaController.buscarPorNomeFantasia(nomeFantasia);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(empresas, response.getBody());
        verify(empresaRepository, times(1)).findByNomeFantasiaContaining(nomeFantasia);
    }
}
