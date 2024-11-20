package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoDto;
import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoSemReferenciaDto;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.domain.entities.Aluno;
import com.studyhub.sth.domain.entities.InstituicaoEnsino;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IAlunoService;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.repositories.IAlunoRepository;
import com.studyhub.sth.domain.repositories.IUsuarioRepository;
import com.studyhub.sth.domain.repositories.InstituicaoEnsinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoService implements IAlunoService {
    @Autowired
    private IAlunoRepository alunoRepositorio;
    @Autowired
    private IUsuarioRepository usuarioRepositorio;
    @Autowired
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;
    @Autowired
    private IMapper mapper;

    @Override
    @Transactional
    public AlunoDto criar(AlunoCreateDto novoAlunoDto) throws ElementoNaoEncontradoExcecao {
        Usuario usuario = this.mapper.map(novoAlunoDto.getNovoUsuarioDto(), Usuario.class);
        this.usuarioRepositorio.save(usuario);

        InstituicaoEnsino instituicaoEnsino = instituicaoEnsinoRepository.findById(novoAlunoDto.getInstituicaoEnsinoId()).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar a instituição de ensino do aluno."));

        Aluno aluno = this.mapper.map(novoAlunoDto, Aluno.class);
        aluno.setUsuario(usuario);
        aluno.setInstituicaoEnsino(instituicaoEnsino);

        this.alunoRepositorio.save(aluno);
        return this.mapper.map(aluno, AlunoDto.class);
    }

    @Override
    @Transactional
    public AlunoDto atualizar(UUID alunoId, AlunoUpdateDto alunoAtualizadoDto) throws ElementoNaoEncontradoExcecao {
        Aluno aluno = this.alunoRepositorio.findById(alunoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível atualizar o aluno."));
        Usuario usuario = aluno.getUsuario();

        aluno.atualizar(alunoAtualizadoDto);
        usuario.atualizar(alunoAtualizadoDto.getUsuarioAtualizadoDto());

        this.usuarioRepositorio.save(usuario);
        this.alunoRepositorio.save(aluno);

        AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
        alunoDto.setUsuarioDto(this.mapper.map(aluno.getUsuario(), UsuarioDto.class));

        return alunoDto;
    }

    @Override
    public AlunoDto detalhar(UUID alunoId) throws ElementoNaoEncontradoExcecao {
        Aluno aluno = this.alunoRepositorio.findById(alunoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar o aluno."));
        AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
        alunoDto.setUsuarioDto(this.mapper.map(aluno.getUsuario(), UsuarioDto.class));
        alunoDto.setInstituicaoEnsinoDto(this.mapper.map(aluno.getInstituicaoEnsino(), InstituicaoEnsinoSemReferenciaDto.class));

        return alunoDto;
    }

    @Override
    public List<AlunoDto> listarTodos() {
        List<Aluno> alunos = this.alunoRepositorio.findAll();

        return alunos.stream().map(aluno -> {
            AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
            UsuarioDto u = this.mapper.map(aluno.getUsuario(), UsuarioDto.class);
            InstituicaoEnsinoSemReferenciaDto iesdto = this.mapper.map(aluno.getInstituicaoEnsino(), InstituicaoEnsinoSemReferenciaDto.class);
            alunoDto.setUsuarioDto(u);
            alunoDto.setInstituicaoEnsinoDto(iesdto);

            return alunoDto;
        }).toList();
    }

    @Override
    public List<AlunoDto> listarPorPeriodo(int periodo) {
        List<AlunoDto> alunos = this.alunoRepositorio.findAlunosByPeriodo(periodo).stream().map(aluno -> this.mapper.map(aluno, AlunoDto.class)).toList();

        return alunos;
    }
}
