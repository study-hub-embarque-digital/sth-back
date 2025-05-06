package com.studyhub.sth.unitarios.api.controllers;

import com.studyhub.sth.api.controllers.SquadController;
import com.studyhub.sth.application.dtos.squad.SquadCreateDTO;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.application.dtos.squad.SquadUpdateDTO;
import com.studyhub.sth.domain.before.services.ISquadService;
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

class SquadControllerTest {

    @Mock
    private ISquadService squadService;

    @InjectMocks
    private SquadController squadController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSquads_ComSucesso() {
        // Arrange
        List<SquadDTO> squads = Arrays.asList(new SquadDTO(), new SquadDTO());
        when(squadService.findAll()).thenReturn(squads);

        // Act
        ResponseEntity<List<SquadDTO>> response = squadController.getAllSquads();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(squads, response.getBody());
        verify(squadService, times(1)).findAll();
    }

    @Test
    void getSquadById_ComSucesso() {
        // Arrange
        UUID squadId = UUID.randomUUID();
        SquadDTO squad = new SquadDTO();
        when(squadService.findById(squadId)).thenReturn(squad);

        // Act
        ResponseEntity<SquadDTO> response = squadController.getSquadById(squadId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(squad, response.getBody());
        verify(squadService, times(1)).findById(squadId);
    }

    @Test
    void createSquad_ComSucesso() {
        // Arrange
        SquadCreateDTO squadCreateDTO = new SquadCreateDTO();
        SquadDTO squadDTO = new SquadDTO();
        when(squadService.save(squadCreateDTO)).thenReturn(squadDTO);

        // Act
        ResponseEntity<SquadDTO> response = squadController.createSquad(squadCreateDTO);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(squadDTO, response.getBody());
        verify(squadService, times(1)).save(squadCreateDTO);
    }

    @Test
    void updateSquad_ComSucesso() {
        // Arrange
        UUID squadId = UUID.randomUUID();
        SquadUpdateDTO squadUpdateDTO = new SquadUpdateDTO();
        SquadDTO updatedSquad = new SquadDTO();
        when(squadService.update(squadId, squadUpdateDTO)).thenReturn(updatedSquad);

        // Act
        ResponseEntity<SquadDTO> response = squadController.updateSquad(squadId, squadUpdateDTO);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedSquad, response.getBody());
        verify(squadService, times(1)).update(squadId, squadUpdateDTO);
    }

    @Test
    void deleteSquad_ComSucesso() {
        // Arrange
        UUID squadId = UUID.randomUUID();

        // Act
        ResponseEntity<Void> response = squadController.deleteSquad(squadId);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(squadService, times(1)).deleteById(squadId);
    }

    @Test
    void buscarSquadPorNome_ComSucesso() {
        // Arrange
        String nome = "Squad Teste";
        SquadDTO squad = new SquadDTO();
        when(squadService.findBySquadNomeContainsIgnoreCase(nome)).thenReturn(squad);

        // Act
        ResponseEntity<SquadDTO> response = squadController.buscarSquadPorNome(nome);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(squad, response.getBody());
        verify(squadService, times(1)).findBySquadNomeContainsIgnoreCase(nome);
    }
}
