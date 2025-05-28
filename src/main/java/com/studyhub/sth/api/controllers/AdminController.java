package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.alunos.*;
import com.studyhub.sth.application.dtos.empresas.*;
import com.studyhub.sth.application.dtos.instituicaoEnsino.*;
import com.studyhub.sth.application.dtos.mentor.*;
import com.studyhub.sth.application.dtos.representante.*;
import com.studyhub.sth.application.dtos.squad.*;
import com.studyhub.sth.domain.enums.Periodo;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    // === Aluno ===
    @PostMapping("/alunos")
    public ResponseEntity<AlunoDto> createAluno(@RequestBody AlunoCreateDto dto) throws Exception {
        return ResponseEntity.ok(adminService.createAluno(dto));
    }

    @PutMapping("/alunos/{id}")
    public ResponseEntity<AlunoDto> updateAluno(@PathVariable UUID id, @RequestBody AlunoUpdateDto dto) throws Exception {
        return ResponseEntity.ok(adminService.updateAluno(id, dto));
    }

    @GetMapping("/alunos/{id}")
    public ResponseEntity<AlunoDto> getAluno(@PathVariable UUID id) throws ElementoNaoEncontradoExcecao {
        return ResponseEntity.ok(adminService.getAlunoById(id));
    }

    @GetMapping("/alunos")
    public ResponseEntity<List<AlunoDto>> listAlunos(@RequestParam(required = false) Periodo periodo) {
        if (periodo != null)
            return ResponseEntity.ok(adminService.listAlunosByPeriodo(periodo));
        return ResponseEntity.ok(adminService.listAlunos());
    }

    // === Empresa ===
    @PostMapping("/empresas")
    public ResponseEntity<EmpresaDto> createEmpresa(@RequestBody EmpresaCreateDto dto) {
        return ResponseEntity.ok(adminService.createEmpresa(dto));
    }

    @PutMapping("/empresas/{id}")
    public ResponseEntity<EmpresaDto> updateEmpresa(@PathVariable UUID id, @RequestBody EmpresaUpdateDto dto) {
        return ResponseEntity.ok(adminService.updateEmpresa(id, dto));
    }

    @GetMapping("/empresas/{id}")
    public ResponseEntity<EmpresaDto> getEmpresa(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.getEmpresaById(id));
    }

    @GetMapping("/empresas")
    public ResponseEntity<List<EmpresaListDto>> listEmpresas() {
        return ResponseEntity.ok(adminService.listEmpresas());
    }

    @DeleteMapping("/empresas/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable UUID id) {
        adminService.deleteEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    // === Instituição de Ensino ===
    @PostMapping("/instituicoes")
    public ResponseEntity<InstituicaoEnsinoDto> createInstituicao(@RequestBody InstituicaoEnsinoCreateDto dto) {
        return ResponseEntity.ok(adminService.createInstituicaoEnsino(dto));
    }

    @PutMapping("/instituicoes/{id}")
    public ResponseEntity<InstituicaoEnsinoDto> updateInstituicao(@PathVariable UUID id, @RequestBody InstituicaoEnsinoUpdateDto dto) {
        return ResponseEntity.ok(adminService.updateInstituicaoEnsino(id, dto));
    }

    @GetMapping("/instituicoes/{id}")
    public ResponseEntity<InstituicaoEnsinoDto> getInstituicao(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.getInstituicaoEnsinoById(id));
    }

    @GetMapping("/instituicoes")
    public ResponseEntity<List<InstituicaoEnsinoListDto>> listInstituicoes() {
        return ResponseEntity.ok(adminService.listInstituicoesEnsino());
    }

    @DeleteMapping("/instituicoes/{id}")
    public ResponseEntity<Void> deleteInstituicao(@PathVariable UUID id) {
        adminService.deleteInstituicaoEnsino(id);
        return ResponseEntity.noContent().build();
    }

    // === Mentor ===
    @PostMapping("/mentores")
    public ResponseEntity<MentorDto> createMentor(@RequestBody MentorCreateDto dto) throws Exception {
        return ResponseEntity.ok(adminService.createMentor(dto));
    }

    @PutMapping("/mentores/{id}")
    public ResponseEntity<MentorDto> updateMentor(@PathVariable UUID id, @RequestBody MentorUpdateDto dto) throws ElementoNaoEncontradoExcecao {
        return ResponseEntity.ok(adminService.updateMentor(id, dto));
    }

    @GetMapping("/mentores/{id}")
    public ResponseEntity<MentorDto> getMentor(@PathVariable UUID id) throws ElementoNaoEncontradoExcecao {
        return ResponseEntity.ok(adminService.getMentorById(id));
    }

    @GetMapping("/mentores")
    public ResponseEntity<List<MentorDto>> listMentores() {
        return ResponseEntity.ok(adminService.listMentores());
    }

    @DeleteMapping("/mentores/{id}")
    public ResponseEntity<Void> deleteMentor(@PathVariable UUID id) throws ElementoNaoEncontradoExcecao {
        adminService.deleteMentorById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mentores/{id}/squads")
    public ResponseEntity<List<SquadDTO>> getMentorSquads(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.listSquadsForMentor(id));
    }

    // === Representante ===
    @PostMapping("/representantes")
    public ResponseEntity<RepresentanteDto> createRepresentante(@RequestBody RepresentanteCreateDto dto) throws Exception {
        return ResponseEntity.ok(adminService.createRepresentante(dto));
    }

    @PutMapping("/representantes/{id}")
    public ResponseEntity<RepresentanteDto> updateRepresentante(@PathVariable UUID id, @RequestBody RepresentanteUpdateDto dto) {
        return ResponseEntity.ok(adminService.updateRepresentante(id, dto));
    }

    @GetMapping("/representantes/{id}")
    public ResponseEntity<RepresentanteDto> getRepresentante(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.getRepresentanteById(id));
    }

    @GetMapping("/representantes")
    public ResponseEntity<List<RepresentanteDto>> listRepresentantes() {
        return ResponseEntity.ok(adminService.listRepresentantes());
    }

    @DeleteMapping("/representantes/{id}")
    public ResponseEntity<Void> deleteRepresentante(@PathVariable UUID id) {
        adminService.deleteRepresentante(id);
        return ResponseEntity.noContent().build();
    }

    // === Squad ===
    @PostMapping("/squads")
    public ResponseEntity<SquadDTO> createSquad(@RequestBody SquadCreateDTO dto) {
        return ResponseEntity.ok(adminService.createSquad(dto));
    }

    @PutMapping("/squads/{id}")
    public ResponseEntity<SquadDTO> updateSquad(@PathVariable UUID id, @RequestBody SquadUpdateDTO dto) {
        return ResponseEntity.ok(adminService.updateSquad(id, dto));
    }

    @GetMapping("/squads/{id}")
    public ResponseEntity<SquadListDTO> getSquad(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.getSquadById(id));
    }

    @GetMapping("/squads")
    public ResponseEntity<List<SquadDTO>> listSquads() {
        return ResponseEntity.ok(adminService.listAllSquads());
    }

    @DeleteMapping("/squads/{id}")
    public ResponseEntity<Void> deleteSquad(@PathVariable UUID id) {
        adminService.deleteSquad(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/squads/buscar")
    public ResponseEntity<SquadDTO> findSquadByName(@RequestParam String nome) {
        return ResponseEntity.ok(adminService.findSquadByName(nome));
    }
}
