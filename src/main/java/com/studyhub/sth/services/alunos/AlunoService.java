package com.studyhub.sth.services.alunos;

import com.studyhub.sth.dtos.InstituicaoEnsino.InstituicaoEnsinoDto;
import com.studyhub.sth.dtos.alunos.AlunoAtualizadoDto;
import com.studyhub.sth.dtos.alunos.AlunoDto;
import com.studyhub.sth.dtos.alunos.NovoAlunoDto;
import com.studyhub.sth.dtos.users.UsuarioDto;
import com.studyhub.sth.entities.Aluno;
import com.studyhub.sth.entities.InstituicaoEnsino;
import com.studyhub.sth.entities.Usuario;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.repositories.IAlunoRepositorio;
import com.studyhub.sth.repositories.IUsuarioRepositorio;
import com.studyhub.sth.repositories.InstituicaoEnsinoRepository;
import com.studyhub.sth.services.usuarios.IUsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoService implements IAlunoService {
    @Autowired
    private IAlunoRepositorio alunoRepositorio;
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;
    @Autowired
    private IMapper mapper;

    @Override
    @Transactional
    public AlunoDto criar(NovoAlunoDto novoAlunoDto) throws ElementoNaoEncontradoExcecao {
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
    public AlunoDto atualizar(UUID alunoId, AlunoAtualizadoDto alunoAtualizadoDto) throws ElementoNaoEncontradoExcecao {
        Aluno aluno = this.alunoRepositorio.findById(alunoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível atualizar o aluno."));
        Usuario usuario = aluno.getUsuario();

        aluno.atualizar(alunoAtualizadoDto);
        usuario.atualizar(alunoAtualizadoDto.getUsuarioAtualizadoDto());

        this.usuarioRepositorio.save(usuario);
        this.alunoRepositorio.save(aluno);

        AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
        alunoDto.setUsuario(this.mapper.map(aluno.getUsuario(), UsuarioDto.class));

        return alunoDto;
    }

    @Override
    public AlunoDto detalhar(UUID alunoId) throws ElementoNaoEncontradoExcecao {
        Aluno aluno = this.alunoRepositorio.findById(alunoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar o aluno."));
        AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
        alunoDto.setUsuario(this.mapper.map(aluno.getUsuario(), UsuarioDto.class));
        alunoDto.setInstituicaoEnsino(this.mapper.map(aluno.getInstituicaoEnsino(), InstituicaoEnsinoDto.class));

        return alunoDto;
    }

    @Override
    public List<AlunoDto> listarTodos() {
        List<Aluno> alunos = this.alunoRepositorio.findAll();

        return alunos.stream().map(aluno -> {
            AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
            alunoDto.setUsuario(this.mapper.map(aluno.getUsuario(), UsuarioDto.class));
            alunoDto.setInstituicaoEnsino(this.mapper.map(aluno.getInstituicaoEnsino(), InstituicaoEnsinoDto.class));

            return alunoDto;
        }).toList();
    }

    @Override
    public List<AlunoDto> listarPorPeriodo(int periodo) {
        List<AlunoDto> alunos = this.alunoRepositorio.findAlunosByPeriodo(periodo).stream().map(aluno -> this.mapper.map(aluno, AlunoDto.class)).toList();

        return alunos;
    }
}
