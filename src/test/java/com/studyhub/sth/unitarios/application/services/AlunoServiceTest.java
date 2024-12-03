package com.studyhub.sth.unitarios.application.services;

import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.services.AlunoService;
import com.studyhub.sth.domain.entities.Aluno;
import com.studyhub.sth.domain.entities.InstituicaoEnsino;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.repositories.IAlunoRepository;
import com.studyhub.sth.domain.repositories.IUsuarioRepository;
import com.studyhub.sth.domain.repositories.InstituicaoEnsinoRepository;
import com.studyhub.sth.libs.mapper.IMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlunoServiceTest {

    @Mock
    private IAlunoRepository alunoRepository;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @Mock
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private AlunoService alunoService;

    private InstituicaoEnsino instituicaoEnsino;
    private Usuario usuario;
    private Aluno aluno;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        instituicaoEnsino = new InstituicaoEnsino();
        instituicaoEnsino.setInstituicaoEnsinoId(UUID.randomUUID());
        instituicaoEnsino.setNome("Instituição Teste");

        usuario = new Usuario();
        usuario.setUsuarioId(UUID.randomUUID());
        usuario.setNome("Usuário Teste");
        usuario.setEmail("usuario@teste.com");
        usuario.setSenha("123456");

        aluno = new Aluno();
        aluno.setAlunoId(UUID.randomUUID());
        aluno.setPeriodo(1);
        aluno.setUsuario(usuario);
        aluno.setInstituicaoEnsino(instituicaoEnsino);
    }

    @Test
    void criarAlunoComSucesso() throws ElementoNaoEncontradoExcecao {
        AlunoCreateDto alunoCreateDto = new AlunoCreateDto();
        alunoCreateDto.setPeriodo(1);
        alunoCreateDto.setInstituicaoEnsinoId(instituicaoEnsino.getInstituicaoEnsinoId());

        Usuario usuarioMock = new Usuario();
        usuarioMock.setUsuarioId(UUID.randomUUID());
        usuarioMock.setNome("Usuário Teste");

        when(mapper.map(alunoCreateDto.getNovoUsuarioDto(), Usuario.class)).thenReturn(usuarioMock);
        when(instituicaoEnsinoRepository.findById(instituicaoEnsino.getInstituicaoEnsinoId())).thenReturn(Optional.of(instituicaoEnsino));
        when(mapper.map(alunoCreateDto, Aluno.class)).thenReturn(aluno);
        when(mapper.map(aluno, AlunoDto.class)).thenReturn(new AlunoDto());

        AlunoDto alunoDto = alunoService.criar(alunoCreateDto);

        verify(usuarioRepository).save(usuarioMock);
        verify(alunoRepository).save(aluno);
        assertNotNull(alunoDto);
    }

    @Test
    void criarAlunoInstituicaoNaoEncontrada() {
        AlunoCreateDto alunoCreateDto = new AlunoCreateDto();
        alunoCreateDto.setPeriodo(1);
        alunoCreateDto.setInstituicaoEnsinoId(UUID.randomUUID());

        when(instituicaoEnsinoRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ElementoNaoEncontradoExcecao.class, () -> alunoService.criar(alunoCreateDto));
    }

    @Test
    void detalharAlunoComSucesso() throws ElementoNaoEncontradoExcecao {
        when(alunoRepository.findById(aluno.getAlunoId())).thenReturn(Optional.of(aluno));
        when(mapper.map(aluno, AlunoDto.class)).thenReturn(new AlunoDto());

        AlunoDto alunoDto = alunoService.detalhar(aluno.getAlunoId());

        assertNotNull(alunoDto);
        verify(alunoRepository).findById(aluno.getAlunoId());
    }

    @Test
    void detalharAlunoNaoEncontrado() {
        when(alunoRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ElementoNaoEncontradoExcecao.class, () -> alunoService.detalhar(UUID.randomUUID()));
    }
}