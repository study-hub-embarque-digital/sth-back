package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.empresas.EmpresaListDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoListDto;
import com.studyhub.sth.application.services.TokenService;
import com.studyhub.sth.domain.before.annotations.CurrentUser;
import com.studyhub.sth.domain.before.entities.Usuario;
import com.studyhub.sth.domain.before.services.IEmpresaService;
import com.studyhub.sth.domain.before.services.IInstituicaoEnsinoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utils")
@Tag(name = "Utils Controller")
public class UtilsController {
    @Autowired
    IInstituicaoEnsinoService instituicaoEnsinoService;
    @Autowired
    IEmpresaService empresaService;
//    @Autowired
//    DeepSeakService deepSeakService;
    @Autowired
    TokenService tokenService;

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

//    @PostMapping("/ia-teste")
//    @Operation(summary = "Listagem de instituições de ensino")
//    public ResponseEntity<DSPromptResponse> Teste() throws JsonProcessingException {
//        return ResponseEntity.ok(deepSeakService.sendRequest());
//    }

    @PostMapping("/token-teste")
    @Operation(summary = "Listagem de instituições de ensino")
    public ResponseEntity<String> Teste(@CurrentUser Usuario usuario) throws Exception {
        return ResponseEntity.ok(tokenService.generateJitsiToken(usuario, "*"));
    }
}
