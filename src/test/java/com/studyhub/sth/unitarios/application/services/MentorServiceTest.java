package com.studyhub.sth.unitarios.application.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.studyhub.sth.application.dtos.mentor.MentorUpdateDto;
import com.studyhub.sth.application.dtos.mentor.MentorDto;
import com.studyhub.sth.application.dtos.mentor.MentorCreateDto;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.application.dtos.users.UsuarioUpdateDto;
import com.studyhub.sth.application.services.MentorService;
import com.studyhub.sth.domain.entities.Mentor;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.repositories.IMentorRepository;
import com.studyhub.sth.domain.repositories.ISquadRepository;
import com.studyhub.sth.domain.repositories.IUsuarioRepository;
import com.studyhub.sth.libs.mapper.IMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class MentorServiceTest {

    @Mock
    private IMentorRepository mentorRepository;

    @Mock
    private IUsuarioRepository usuarioRepositorio;

    @Mock
    private IMapper mapper;

    @Mock
    private ISquadRepository squadRepositorio;

    @InjectMocks
    private MentorService mentorService;

    private Mentor mentor;
    private MentorDto mentorDto;
    private Usuario usuario;
    private UUID mentorId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mentorId = UUID.randomUUID();
        mentor = new Mentor();
        mentor.setId(mentorId);
        mentor.setUsuario(new Usuario());
        mentor.getUsuario().setNome("Test Mentor");
        mentor.getUsuario().setEmail("test@mentor.com");

        mentorDto = new MentorDto();
        mentorDto.setId(mentorId);
        mentorDto.setUsuarioDto(new UsuarioDto());
        mentorDto.getUsuarioDto().setNome("Test Mentor");
        mentorDto.getUsuarioDto().setEmail("test@mentor.com");

        usuario = new Usuario();
        usuario.setNome("Test Mentor");
        usuario.setEmail("test@mentor.com");
    }

    @Test
    public void testCriar() {
        MentorCreateDto createDto = new MentorCreateDto();

        when(mapper.map(createDto, Mentor.class)).thenReturn(mentor);
        when(mapper.map(mentor, MentorDto.class)).thenReturn(mentorDto);
        when(mapper.map(mentor.getUsuario(), UsuarioDto.class)).thenReturn(mentorDto.getUsuarioDto());
        when(mentorRepository.save(mentor)).thenReturn(mentor);

        MentorDto result = mentorService.criar(createDto);

        assertNotNull(result);
        verify(mentorRepository, times(1)).save(mentor);
    }

    @Test
    public void testListar() {
        when(mentorRepository.findAll()).thenReturn(List.of(mentor));
        when(mapper.map(mentor, MentorDto.class)).thenReturn(mentorDto);
        when(mapper.map(mentor.getUsuario(), UsuarioDto.class)).thenReturn(mentorDto.getUsuarioDto());

        List<MentorDto> result = mentorService.listar();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Mentor", result.get(0).getUsuarioDto().getNome());
    }

    @Test
    public void testBuscarPorId() throws ElementoNaoEncontradoExcecao {
        when(mentorRepository.findById(mentorId)).thenReturn(Optional.of(mentor));
        when(mapper.map(mentor, MentorDto.class)).thenReturn(mentorDto);
        when(mapper.map(mentor.getUsuario(), UsuarioDto.class)).thenReturn(mentorDto.getUsuarioDto());

        MentorDto result = mentorService.buscarPorId(mentorId);

        assertNotNull(result);
        assertEquals("Test Mentor", result.getUsuarioDto().getNome());
    }

    @Test
    public void testBuscarPorIdThrowsElementoNaoEncontradoExcecao() {
        when(mentorRepository.findById(mentorId)).thenReturn(Optional.empty());

        assertThrows(ElementoNaoEncontradoExcecao.class, () -> mentorService.buscarPorId(mentorId));
    }

    @Test
    public void testDeletarPorId() throws ElementoNaoEncontradoExcecao {
        when(mentorRepository.findById(mentorId)).thenReturn(Optional.of(mentor));
        when(mentorRepository.getAllSquad(mentorId)).thenReturn(List.of());

        mentorService.deletarPorId(mentorId);

        verify(mentorRepository, times(1)).delete(mentor);
        verify(squadRepositorio, times(1)).deleteAllInBatch(List.of());
    }

    @Test
    public void testDeletarPorIdThrowsElementoNaoEncontradoExcecao() {
        when(mentorRepository.findById(mentorId)).thenReturn(Optional.empty());

        assertThrows(ElementoNaoEncontradoExcecao.class, () -> mentorService.deletarPorId(mentorId));
    }

    @Test
    public void testAtualizar() throws ElementoNaoEncontradoExcecao {
        MentorUpdateDto updateDto = new MentorUpdateDto();
        UsuarioUpdateDto usuarioUpdateDto = new UsuarioUpdateDto();
        usuarioUpdateDto.setNome("Updated Mentor");
        mentorDto.setUsuarioDto(new UsuarioDto());
        mentorDto.getUsuarioDto().setNome("Updated Mentor");

        // Configura o mentor e o DTO de retorno
        when(mentorRepository.findById(mentorId)).thenReturn(Optional.of(mentor));
        when(mapper.map(mentor, MentorDto.class)).thenReturn(mentorDto);
        when(mapper.map(mentor.getUsuario(), UsuarioUpdateDto.class)).thenReturn(usuarioUpdateDto);

        // Verifica se o nome do mentor será atualizado
        when(mentorRepository.save(mentor)).thenReturn(mentor);

        // Executa a atualização
        MentorDto result = mentorService.atualizar(mentorId, updateDto);

        // Verifica se o resultado não é nulo e se o nome foi atualizado
        assertNotNull(result);
        assertEquals(mentorId, result.getId());

        // Verifica se a atualização foi realizada no repositório
        verify(mentorRepository, times(1)).save(mentor);
    }

    @Test
    public void testAtualizarThrowsElementoNaoEncontradoExcecao() {
        MentorUpdateDto updateDto = new MentorUpdateDto();
        updateDto.setUsuarioDto(new UsuarioUpdateDto());
        updateDto.getUsuarioDto().setNome("Updated Mentor");

        when(mentorRepository.findById(mentorId)).thenReturn(Optional.empty());

        assertThrows(ElementoNaoEncontradoExcecao.class, () -> mentorService.atualizar(mentorId, updateDto));
    }

    @Test
    public void testListarSquads() {
        when(mentorRepository.getAllSquad(mentorId)).thenReturn(List.of());
        when(mapper.map(null, SquadDTO.class)).thenReturn(null);

        List<SquadDTO> result = mentorService.listarSquads(mentorId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testBuscarPorNome() throws ElementoNaoEncontradoExcecao {
        when(mentorRepository.findByUsuarioNomeContainsIgnoreCase("Test Mentor")).thenReturn(Optional.of(mentor));
        when(mapper.map(mentor, MentorDto.class)).thenReturn(mentorDto);
        when(mapper.map(mentor.getUsuario(), UsuarioDto.class)).thenReturn(mentorDto.getUsuarioDto());

        MentorDto result = mentorService.buscarPorNome("Test Mentor");

        assertNotNull(result);
        assertEquals("Test Mentor", result.getUsuarioDto().getNome());
    }

    @Test
    public void testBuscarPorNomeThrowsElementoNaoEncontradoExcecao() {
        when(mentorRepository.findByUsuarioNomeContainsIgnoreCase("Test Mentor")).thenReturn(Optional.empty());

        assertThrows(ElementoNaoEncontradoExcecao.class, () -> mentorService.buscarPorNome("Test Mentor"));
    }
}
