package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.domain.enums.Periodo;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IAlunoService {
    public String criar(AlunoCreateDto novoAlunoDto) throws Exception;
    public AlunoDto atualizar(UUID alunoId, AlunoUpdateDto alunoAtualizadoDto) throws ElementoNaoEncontradoExcecao, IOException;
    public AlunoDto detalhar(UUID alunoId) throws ElementoNaoEncontradoExcecao;
    public List<AlunoDto> listarTodos();
    public List<AlunoDto> listarPorPeriodo(Periodo periodo);
}
