package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.application.dtos.graficos.AlunosAtivosPorInstituicaoDto;
import com.studyhub.sth.application.dtos.graficos.AlunosPeriodoTrabalhoDto;
import com.studyhub.sth.application.dtos.graficos.AlunosPorGeneroDto;
import com.studyhub.sth.application.dtos.graficos.CursoCountDto;
import com.studyhub.sth.domain.entities.Aluno;
import com.studyhub.sth.domain.enums.Periodo;
import com.studyhub.sth.domain.enums.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, UUID> {
    List<Aluno> findAlunosByPeriodo(Periodo periodo);

    @Query("SELECT a.curso AS curso, COUNT(a) AS quantidade FROM alunos a GROUP BY a.curso")
    List<CursoCountDto> countAlunosByCurso();

    @Query("""
        SELECT 
            i.nomeFantasia AS nomeInstituicao,
            COUNT(a) AS totalAlunos,
            SUM(CASE WHEN u.isActive = true THEN 1 ELSE 0 END) AS alunosAtivos
        FROM alunos a
        JOIN a.instituicaoEnsino i
        JOIN a.usuario u
        GROUP BY i.nomeFantasia
    """)
    List<AlunosAtivosPorInstituicaoDto> buscarAlunosAtivosPorInstituicao();

    @Query("""
        SELECT a.periodo AS periodo,
               COUNT(a) AS totalAlunos,
               SUM(CASE WHEN a.isWorkingInIt = true THEN 1 ELSE 0 END) AS totalTrabalhando
        FROM alunos a
        GROUP BY a.periodo
    """)
    List<AlunosPeriodoTrabalhoDto> countAlunosPorPeriodoComTrabalhando();

    @Query("""
        SELECT u.gender AS genero, COUNT(a) AS total
        FROM alunos a
        JOIN a.usuario u
        GROUP BY u.gender
    """)
    List<AlunosPorGeneroDto> countAlunosPorGenero();

    List<Aluno> findByInstituicaoEnsino_InstituicaoEnsinoIdAndPeriodoAndTurnoAndSquadIsNull(
            UUID instituicaoEnsinoId,
            Periodo periodo,
            Turno turno
    );
}
