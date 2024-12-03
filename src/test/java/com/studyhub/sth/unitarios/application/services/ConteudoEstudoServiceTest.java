package com.studyhub.sth.unitarios.application.services;

import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoCreateDto;
import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoDto;
import com.studyhub.sth.application.dtos.conteudoEstudo.ConteudoEstudoUpdateDto;
import com.studyhub.sth.application.services.ConteudoEstudoService;
import com.studyhub.sth.domain.entities.ConteudoEstudo;
import com.studyhub.sth.domain.entities.Room;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.repositories.IConteudoEstudoRepository;
import com.studyhub.sth.domain.repositories.IRoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConteudoEstudoServiceTest {

    @Mock
    private IConteudoEstudoRepository conteudoEstudoRepository;

    @Mock
    private IRoomRepository roomRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private ConteudoEstudoService conteudoEstudoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarConteudoEstudoDeveSalvarEMapearConteudo() {
        ConteudoEstudoCreateDto dto = new ConteudoEstudoCreateDto();
        ConteudoEstudo conteudoEstudo = new ConteudoEstudo();
        ConteudoEstudoCreateDto retornoDto = new ConteudoEstudoCreateDto();

        when(mapper.map(dto, ConteudoEstudo.class)).thenReturn(conteudoEstudo);
        when(conteudoEstudoRepository.save(conteudoEstudo)).thenReturn(conteudoEstudo);
        when(mapper.map(conteudoEstudo, ConteudoEstudoCreateDto.class)).thenReturn(retornoDto);

        ConteudoEstudoCreateDto resultado = conteudoEstudoService.criarConteudoEstudo(dto);

        assertNotNull(resultado);
        assertEquals(retornoDto, resultado);
        verify(mapper).map(dto, ConteudoEstudo.class);
        verify(conteudoEstudoRepository).save(conteudoEstudo);
        verify(mapper).map(conteudoEstudo, ConteudoEstudoCreateDto.class);
    }

    @Test
    void listarConteudosEstudoDeveRetornarListaDeConteudos() {
        List<ConteudoEstudo> conteudos = List.of(new ConteudoEstudo(), new ConteudoEstudo());
        List<ConteudoEstudoCreateDto> conteudosDto = List.of(new ConteudoEstudoCreateDto(), new ConteudoEstudoCreateDto());

        when(conteudoEstudoRepository.findAll()).thenReturn(conteudos);
        when(mapper.map(any(ConteudoEstudo.class), eq(ConteudoEstudoCreateDto.class)))
                .thenReturn(new ConteudoEstudoCreateDto())
                .thenReturn(new ConteudoEstudoCreateDto());

        List<ConteudoEstudoCreateDto> resultado = conteudoEstudoService.listarConteudosEstudo();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(conteudoEstudoRepository).findAll();
        verify(mapper, times(2)).map(any(ConteudoEstudo.class), eq(ConteudoEstudoCreateDto.class));
    }

    @Test
    void obterConteudoEstudoPorIdDeveRetornarConteudoDto() {
        UUID id = UUID.randomUUID();
        ConteudoEstudo conteudoEstudo = new ConteudoEstudo();
        ConteudoEstudoDto conteudoEstudoDto = new ConteudoEstudoDto();

        when(conteudoEstudoRepository.findById(id)).thenReturn(Optional.of(conteudoEstudo));
        when(mapper.map(conteudoEstudo, ConteudoEstudoDto.class)).thenReturn(conteudoEstudoDto);

        ConteudoEstudoDto resultado = conteudoEstudoService.obterConteudoEstudoPorId(id);

        assertNotNull(resultado);
        assertEquals(conteudoEstudoDto, resultado);
        verify(conteudoEstudoRepository).findById(id);
        verify(mapper).map(conteudoEstudo, ConteudoEstudoDto.class);
    }

    @Test
    void atualizarConteudoEstudoDeveAtualizarEMapearConteudo() {
        UUID id = UUID.randomUUID();
        ConteudoEstudoUpdateDto updateDto = new ConteudoEstudoUpdateDto();
        updateDto.setLink("novo-link");
        updateDto.setRoomId(UUID.randomUUID());

        ConteudoEstudo conteudoEstudo = new ConteudoEstudo();
        Room room = new Room();
        ConteudoEstudoDto conteudoEstudoDto = new ConteudoEstudoDto();

        when(conteudoEstudoRepository.findById(id)).thenReturn(Optional.of(conteudoEstudo));
        when(roomRepository.findById(updateDto.getRoomId())).thenReturn(Optional.of(room));
        when(mapper.map(conteudoEstudo, ConteudoEstudoDto.class)).thenReturn(conteudoEstudoDto);

        ConteudoEstudoDto resultado = conteudoEstudoService.atualizarConteudoEstudo(id, updateDto);

        assertNotNull(resultado);
        assertEquals(conteudoEstudoDto, resultado);
        assertEquals("novo-link", conteudoEstudo.getLink());
        assertEquals(room, conteudoEstudo.getRoom());
        verify(conteudoEstudoRepository).findById(id);
        verify(roomRepository).findById(updateDto.getRoomId());
        verify(conteudoEstudoRepository).save(conteudoEstudo);
        verify(mapper).map(conteudoEstudo, ConteudoEstudoDto.class);
    }

    @Test
    void deletarConteudoEstudoDeveRemoverConteudo() {
        UUID id = UUID.randomUUID();
        ConteudoEstudo conteudoEstudo = new ConteudoEstudo();

        when(conteudoEstudoRepository.findById(id)).thenReturn(Optional.of(conteudoEstudo));

        assertDoesNotThrow(() -> conteudoEstudoService.deletarConteudoEstudo(id));
        verify(conteudoEstudoRepository).findById(id);
        verify(conteudoEstudoRepository).delete(conteudoEstudo);
    }
}