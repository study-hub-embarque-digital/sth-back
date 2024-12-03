package com.studyhub.sth.unitarios.api.controllers;

import com.studyhub.sth.api.controllers.MentorController;
import com.studyhub.sth.application.dtos.mentor.MentorCreateDto;
import com.studyhub.sth.application.dtos.mentor.MentorDto;
import com.studyhub.sth.application.dtos.mentor.MentorUpdateDto;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IMentorService;
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

class MentorControllerTest {

    @Mock
    private IMentorService mentorService;

    @InjectMocks
    private MentorController mentorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarMentores_ComSucesso() {
        // Arrange
        List<MentorDto> mentores = Arrays.asList(new MentorDto(), new MentorDto());
        when(mentorService.listar()).thenReturn(mentores);

        // Act
        ResponseEntity<List<MentorDto>> response = mentorController.listarMentores();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mentores, response.getBody());
        verify(mentorService, times(1)).listar();
    }

    @Test
    void buscarMentorPorID_ComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID id = UUID.randomUUID();
        MentorDto mentor = new MentorDto();
        when(mentorService.buscarPorId(id)).thenReturn(mentor);

        // Act
        ResponseEntity<MentorDto> response = mentorController.buscarMentorPorID(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mentor, response.getBody());
        verify(mentorService, times(1)).buscarPorId(id);
    }

    @Test
    void deletarMentor_ComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(mentorService).deletarPorId(id);

        // Act
        ResponseEntity response = mentorController.deletarMentor(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(mentorService, times(1)).deletarPorId(id);
    }

    @Test
    void atualizarMentor_ComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        UUID id = UUID.randomUUID();
        MentorUpdateDto mentorUpdateDto = new MentorUpdateDto();
        MentorDto mentorAtualizado = new MentorDto();
        when(mentorService.atualizar(id, mentorUpdateDto)).thenReturn(mentorAtualizado);

        // Act
        ResponseEntity<MentorDto> response = mentorController.atualizarMentor(id, mentorUpdateDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mentorAtualizado, response.getBody());
        verify(mentorService, times(1)).atualizar(id, mentorUpdateDto);
    }

    @Test
    void listarSquadsDeMentor_ComSucesso() {
        // Arrange
        UUID id = UUID.randomUUID();
        List<SquadDTO> squads = Arrays.asList(new SquadDTO(), new SquadDTO());
        when(mentorService.listarSquads(id)).thenReturn(squads);

        // Act
        ResponseEntity<List<SquadDTO>> response = mentorController.listarSquadsDeMentor(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(squads, response.getBody());
        verify(mentorService, times(1)).listarSquads(id);
    }

    @Test
    void buscarMentorPorNome_ComSucesso() throws ElementoNaoEncontradoExcecao {
        // Arrange
        String nome = "Mentor Teste";
        MentorDto mentor = new MentorDto();
        when(mentorService.buscarPorNome(nome)).thenReturn(mentor);

        // Act
        ResponseEntity<MentorDto> response = mentorController.buscarMentorPorNome(nome);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mentor, response.getBody());
        verify(mentorService, times(1)).buscarPorNome(nome);
    }
}
