package com.studyhub.sth.services.alunos;

import com.studyhub.sth.dtos.alunos.AlunoAtualizadoDto;
import com.studyhub.sth.dtos.alunos.AlunoDto;
import com.studyhub.sth.dtos.alunos.NovoAlunoDto;
import com.studyhub.sth.entities.Aluno;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;

import java.util.List;
import java.util.UUID;

public interface IAlunoService {
    public AlunoDto criar(NovoAlunoDto novoAlunoDto);
    public AlunoDto atualizar(UUID alunoId, AlunoAtualizadoDto alunoAtualizadoDto) throws ElementoNaoEncontradoExcecao;
    public AlunoDto detalhar(UUID alunoId) throws ElementoNaoEncontradoExcecao;
    public List<AlunoDto> listarTodos();
}
