package com.studyhub.sth.integracao.api.controllers;

import com.studyhub.sth.api.controllers.EmpresaController;
import com.studyhub.sth.application.dtos.empresas.EmpresaCreateDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaUpdateDto;
import com.studyhub.sth.application.services.EmpresaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmpresaControllerTest {

    @InjectMocks
    private EmpresaController empresaController;

    @Mock
    private EmpresaService empresaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmpresas_Sucesso() {
        // Arrange
        List<EmpresaDto> empresas = Arrays.asList(new EmpresaDto(), new EmpresaDto());
        when(empresaService.findAll()).thenReturn(empresas);

        // Act
        ResponseEntity<List<EmpresaDto>> response = empresaController.getAllEmpresas();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresas, response.getBody());
    }

    @Test
    public void testGetEmpresaById_Sucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        EmpresaDto empresaDto = new EmpresaDto();
        when(empresaService.findById(id)).thenReturn(empresaDto);

        // Act
        ResponseEntity<EmpresaDto> response = empresaController.getEmpresaById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresaDto, response.getBody());
    }

    @Test
    public void testCreateEmpresa_Sucesso() {
        // Arrange
        EmpresaCreateDto createDto = new EmpresaCreateDto();
        EmpresaDto empresaDto = new EmpresaDto();
        when(empresaService.save(createDto)).thenReturn(empresaDto);

        // Act
        ResponseEntity<EmpresaDto> response = empresaController.createEmpresa(createDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresaDto, response.getBody());
    }

    @Test
    public void testUpdateEmpresa_Sucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        EmpresaUpdateDto updateDto = new EmpresaUpdateDto();
        EmpresaDto empresaDto = new EmpresaDto();
        when(empresaService.update(id, updateDto)).thenReturn(empresaDto);

        // Act
        ResponseEntity<EmpresaDto> response = empresaController.updateEmpresa(id, updateDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresaDto, response.getBody());
    }

    @Test
    public void testDeleteEmpresa_Sucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(empresaService).delete(id);

        // Act
        ResponseEntity<Void> response = empresaController.deleteEmpresa(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(empresaService, times(1)).delete(id);
    }
}
