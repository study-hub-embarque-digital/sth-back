package com.studyhub.sth.services.alunos;

import com.studyhub.sth.dtos.alunos.AlunoAtualizadoDto;
import com.studyhub.sth.dtos.alunos.NovoAlunoDto;
import com.studyhub.sth.entities.Aluno;
import com.studyhub.sth.entities.Usuario;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.repositories.IAlunoRepositorio;
import com.studyhub.sth.repositories.IUsuarioRepositorio;
import com.studyhub.sth.services.usuarios.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AlunoService implements IAlunoService {
    @Autowired
    private IAlunoRepositorio alunoRepositorio;
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private IMapper mapper;

    @Override
    public Aluno criar(UUID usuarioId, NovoAlunoDto novoAlunoDto) throws ElementoNaoEncontradoExcecao {
        Usuario usuario = this.usuarioRepositorio.findById(usuarioId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível cadastrar o aluno."));

        Aluno aluno = this.mapper.map(novoAlunoDto, Aluno.class);
        aluno.setUsuario(usuario);

        this.alunoRepositorio.save(aluno);

        return aluno;
    }

    @Override
    public Aluno atualizar(UUID alunoId, AlunoAtualizadoDto alunoAtualizadoDto) throws ElementoNaoEncontradoExcecao {
        Aluno aluno = this.alunoRepositorio.findById(alunoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível atualizar o aluno."));
        aluno.atualizar(alunoAtualizadoDto);

        this.alunoRepositorio.save(aluno);
        return aluno;
    }

    @Override
    public Aluno detalhar(UUID alunoId) throws ElementoNaoEncontradoExcecao {
        Aluno aluno = this.alunoRepositorio.findById(alunoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar o aluno."));

        return aluno;
    }
}
