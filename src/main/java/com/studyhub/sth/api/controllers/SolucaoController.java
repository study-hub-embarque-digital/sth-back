package com.studyhub.sth.api.controllers;
import com.studyhub.sth.application.dtos.duvida.DuvidaDto;
import com.studyhub.sth.application.dtos.duvida.NewDuvidaDto;
import com.studyhub.sth.application.dtos.duvida.UpdateDuvidaDto;
import com.studyhub.sth.application.dtos.solucao.CreateSolucaoDto;
import com.studyhub.sth.application.dtos.solucao.SolucaoDto;
import com.studyhub.sth.application.dtos.solucao.UpdateSolucaoDto;
import com.studyhub.sth.domain.services.ISolucaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/solucao")
@Tag(name = "Soluções Controller")
public class SolucaoController {
    @Autowired
    private ISolucaoService solucaoService;

    @GetMapping
    public ResponseEntity<List<SolucaoDto>> getAll() {
        return ResponseEntity.ok(this.solucaoService.findAll());
    }

    @PostMapping
    public ResponseEntity<SolucaoDto> create(@RequestBody CreateSolucaoDto solucaoDto) {
        return new ResponseEntity<>(this.solucaoService.create(solucaoDto), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SolucaoDto> update(@PathVariable UUID id, @RequestBody UpdateSolucaoDto updateDuvidaDto) {
        SolucaoDto dto = solucaoService.update(updateDuvidaDto, id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        solucaoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
