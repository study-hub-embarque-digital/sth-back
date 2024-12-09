package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaListDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoListDto;
import com.studyhub.sth.domain.services.IEmpresaService;
import com.studyhub.sth.domain.services.IInstituicaoEnsinoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/utils")
@Tag(name = "Utils Controller")
public class UtilsController {
    @Autowired
    IInstituicaoEnsinoService instituicaoEnsinoService;
    @Autowired
    IEmpresaService empresaService;

    @GetMapping("/instituicoes")
    @Operation(summary = "Listagem de instituições de ensino")
    public ResponseEntity<List<InstituicaoEnsinoListDto>> ObterInstituicoes() {
        return ResponseEntity.ok(instituicaoEnsinoService.list());
    }

    @GetMapping("/empresas")
    @Operation(summary = "Listagem de instituições de ensino")
    public ResponseEntity<List<EmpresaListDto>> ObterEmpresas() {
        return ResponseEntity.ok(empresaService.list());
    }
}
