package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;

import java.util.List;
import java.util.UUID;

public interface IAlunoService {
    public AlunoDto criar(AlunoCreateDto novoAlunoDto) throws ElementoNaoEncontradoExcecao;
    public AlunoDto atualizar(UUID alunoId, AlunoUpdateDto alunoAtualizadoDto) throws ElementoNaoEncontradoExcecao;
    public AlunoDto detalhar(UUID alunoId) throws ElementoNaoEncontradoExcecao;
    public List<AlunoDto> listarTodos();
    public List<AlunoDto> listarPorPeriodo(int periodo);
}
