package com.studyhub.sth.services.alunos;

import com.studyhub.sth.dtos.alunos.AlunoAtualizadoDto;
import com.studyhub.sth.dtos.alunos.NovoAlunoDto;
import com.studyhub.sth.entities.Aluno;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;

import java.util.UUID;

public interface IAlunoService {
    public Aluno criar(UUID usuarioId, NovoAlunoDto novoAlunoDto) throws ElementoNaoEncontradoExcecao;
    public Aluno atualizar(UUID alunoId, AlunoAtualizadoDto alunoAtualizadoDto) throws ElementoNaoEncontradoExcecao;
    public Aluno detalhar(UUID alunoId) throws ElementoNaoEncontradoExcecao;
//    public Aluno create();
}
