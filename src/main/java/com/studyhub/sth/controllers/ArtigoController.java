package com.studyhub.sth.controllers;

import com.studyhub.sth.dtos.artigo.ArtigoDto;
import com.studyhub.sth.services.artigo.IArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/artigo")
public class ArtigoController {

    @Autowired
    private IArtigoService artigoService;

    @GetMapping
    public ResponseEntity<List<ArtigoDto>> listarArtigos(){
        var artigos = this.artigoService.listarArtigos();
        return ResponseEntity.ok(artigos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtigoDto> buscarArtigoPoId(@PathVariable UUID id){
        var artigo = this.artigoService.buscarArtigoPorId(id);
        return ResponseEntity.ok(artigo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarArtigo(@PathVariable UUID id){
        this.artigoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
