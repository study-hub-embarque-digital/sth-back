package com.studyhub.sth.controllers;

import com.studyhub.sth.dtos.empresas.EmpresaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.studyhub.sth.repositories.EmpresaRepository;
import com.studyhub.sth.dtos.empresas.EmpresaCreateDto;
import com.studyhub.sth.dtos.empresas.EmpresaUpdateDto;
import com.studyhub.sth.entities.Empresa;
import com.studyhub.sth.services.empresa.EmpresaService;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private EmpresaRepository empresaRepository;

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
    public Empresa createEmpresa(@RequestBody EmpresaCreateDto empresaDto) {
        return empresaService.save(empresaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable UUID id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{nomeFantasia}")
    public List<Empresa> buscarPorNomeFantasia(@PathVariable String nomeFantasia) {
        return empresaRepository.findByNomeFantasiaContaining(nomeFantasia);
    }

}
