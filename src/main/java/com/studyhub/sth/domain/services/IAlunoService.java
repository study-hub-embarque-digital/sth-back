package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.application.dtos.graficos.AlunosAtivosPorInstituicaoDto;
import com.studyhub.sth.application.dtos.graficos.AlunosPeriodoTrabalhoDto;
import com.studyhub.sth.application.dtos.graficos.AlunosPorGeneroDto;
import com.studyhub.sth.application.dtos.graficos.CursoCountDto;
import com.studyhub.sth.domain.enums.Periodo;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IAlunoService {
    public AlunoDto criar(AlunoCreateDto novoAlunoDto) throws Exception;
    public AlunoDto atualizar(UUID alunoId, AlunoUpdateDto alunoAtualizadoDto) throws ElementoNaoEncontradoExcecao, IOException;
    public AlunoDto detalhar(UUID alunoId) throws ElementoNaoEncontradoExcecao;
    public List<AlunoDto> listarTodos();
    public List<AlunoDto> listarPorPeriodo(Periodo periodo);
    public List<CursoCountDto> buscarQuantidadePorCurso();
    public List<AlunosAtivosPorInstituicaoDto> buscarAlunosAtivosPorIes();
    public List<AlunosPeriodoTrabalhoDto> countAlunosPorPeriodoTrabalhando();
    public List<AlunosPorGeneroDto> countAlunosPorGenero();
}
