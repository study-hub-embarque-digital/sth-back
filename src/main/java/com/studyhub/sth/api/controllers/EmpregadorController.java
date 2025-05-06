package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.empregador.EmpregadorCreateDto;
import com.studyhub.sth.application.dtos.empregador.EmpregadorListDto;
import com.studyhub.sth.application.dtos.empregador.EmpregadorUpdateDto;
import com.studyhub.sth.domain.before.services.IEmpregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/empregadores")
public class EmpregadorController {

    @Autowired
    private IEmpregadorService empregadorService;

    @PostMapping
    public ResponseEntity<EmpregadorListDto> criar(@RequestBody EmpregadorCreateDto dto) {
        EmpregadorListDto empregadorDto = empregadorService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(empregadorDto);
    }

    @GetMapping
    public ResponseEntity<List<EmpregadorListDto>> listar() {
        List<EmpregadorListDto> empregadores = empregadorService.listar();
        return ResponseEntity.ok(empregadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpregadorListDto> buscarPorId(@PathVariable UUID id) {
        EmpregadorListDto empregadorDto = empregadorService.buscarPorId(id);
        return ResponseEntity.ok(empregadorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpregadorListDto> atualizar(@PathVariable UUID id, @RequestBody EmpregadorUpdateDto dto) {
        EmpregadorListDto empregadorDto = empregadorService.atualizar(id, dto);
        return ResponseEntity.ok(empregadorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        empregadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
