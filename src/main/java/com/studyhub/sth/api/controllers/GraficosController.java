package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.graficos.*;
import com.studyhub.sth.domain.services.IAlunoService;
import com.studyhub.sth.domain.services.IEmpresaService;
import com.studyhub.sth.domain.services.ISquadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/graficos")
@Tag(name = "Gráficos Controller")
public class GraficosController {
    @Autowired
    private IAlunoService alunoService;

    @Autowired
    private ISquadService squadService;

    @Autowired
    private IEmpresaService empresaService;

    @GetMapping("/alunos-cursos")
    public ResponseEntity<List<CursoCountDto>> buscarQuantidadePorCurso() {
        var alunosPorCurso = this.alunoService.buscarQuantidadePorCurso();
        return ResponseEntity.ok(alunosPorCurso);
    }

    @GetMapping("/alunos-ativos-ies")
    public ResponseEntity<List<AlunosAtivosPorInstituicaoDto>> buscarAlunosAtivosPorIes() {
        var alunosPorCurso = this.alunoService.buscarAlunosAtivosPorIes();
        return ResponseEntity.ok(alunosPorCurso);
    }

    @GetMapping("/squads-por-empresa")
    public ResponseEntity<List<EmpresaSquadCountDto>> countSquadsPorEmpresa() {
        var countSquadsPorEmpresa = this.squadService.countSquadsPorEmpresa();
        return ResponseEntity.ok(countSquadsPorEmpresa);
    }

    @GetMapping("/alunos-trabalhando-periodo")
    public ResponseEntity<List<AlunosPeriodoTrabalhoDto>> countAlunosPorPeriodoComTrabalhando(){
        var alunosTrabalhandoPeriodo = this.alunoService.countAlunosPorPeriodoTrabalhando();
        return  ResponseEntity.ok(alunosTrabalhandoPeriodo);
    }

    @GetMapping("/alunos-por-genero")
    public ResponseEntity<List<AlunosPorGeneroDto>> countAlunosPorGenero(){
        var alunosPorGenero = this.alunoService.countAlunosPorGenero();
        return ResponseEntity.ok(alunosPorGenero);
    }

    @GetMapping("/empresas-por-ciclo")
    public ResponseEntity<List<EmpresaPorCicloDTO>> findEmpresasPorCiclo(){
        var empresasPorCiclo = empresaService.findEmpresasPorCiclo();
        return ResponseEntity.ok(empresasPorCiclo);
    }

    @GetMapping("squads-demoday-por-ies")
    public ResponseEntity<List<SquadsDemodayPorInstituicaoDTO>> findSquadsSelecionadosPorInstituicao(){
        var squadsPorInstituicao = squadService.findSquadsSelecionadosPorInstituicao();
        return ResponseEntity.ok(squadsPorInstituicao);
    }


}
