package com.studyhub.sth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.studyhub.sth.repositories.EmpresaRepository;
import com.studyhub.sth.dtos.Empresas.NovoEmpresaDto;
import com.studyhub.sth.dtos.Empresas.UpdateEmpresaDto;
import com.studyhub.sth.entities.Empresa;
import com.studyhub.sth.services.Empresa.EmpresaService;
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
    public List<Empresa> getAllEmpresas() {
        return empresaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable UUID id) {
        Optional<Empresa> empresa = empresaService.findById(id);
        return empresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //@GetMapping("/{nome}")
    //public Empresa getEmpresaByName(@PathVariable String name) {
    //    return EmpresaRepository.findByNomeFantasia(name);
    //}

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable("id") UUID id, @RequestBody UpdateEmpresaDto empresaDto) {
        Empresa updatedEmpresa = empresaService.update(id, empresaDto);
        return ResponseEntity.ok(updatedEmpresa);
    }

    @PostMapping
    public Empresa createEmpresa(@RequestBody NovoEmpresaDto empresaDto) {
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
