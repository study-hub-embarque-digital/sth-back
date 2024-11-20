package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.studyhub.sth.domain.repositories.IEmpresaRepository;
import com.studyhub.sth.application.dtos.empresas.EmpresaCreateDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaUpdateDto;
import com.studyhub.sth.domain.entities.Empresa;
import com.studyhub.sth.application.services.EmpresaService;

import java.util.UUID;

@RestController
@RequestMapping("/api/empresas")
@Tag(name = "Empresas Controller")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private IEmpresaRepository IEmpresaRepository;

    @GetMapping
    public List<EmpresaDto> getAllEmpresas() {
        return empresaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> getEmpresaById(@PathVariable UUID id) {
        var empresa = empresaService.findById(id);
        return ResponseEntity.ok(empresa);
    }

    //@GetMapping("/{nome}")
    //public Empresa getEmpresaByName(@PathVariable String name) {
    //    return EmpresaRepository.findByNomeFantasia(name);
    //}

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDto> updateEmpresa(@PathVariable("id") UUID id, @RequestBody EmpresaUpdateDto empresaDto) {
        var updatedEmpresa = empresaService.update(id, empresaDto);
        return ResponseEntity.ok(updatedEmpresa);
    }

    @PostMapping
    public EmpresaDto createEmpresa(@RequestBody EmpresaCreateDto empresaDto) {
        return empresaService.save(empresaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable UUID id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{nomeFantasia}")
    public List<Empresa> buscarPorNomeFantasia(@PathVariable String nomeFantasia) {
        return IEmpresaRepository.findByNomeFantasiaContaining(nomeFantasia);
    }

}
