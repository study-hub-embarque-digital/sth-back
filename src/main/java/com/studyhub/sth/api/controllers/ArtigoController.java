package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.artigo.ArtigoCreateDto;
import com.studyhub.sth.application.dtos.artigo.ArtigoDto;
import com.studyhub.sth.application.dtos.artigo.ArtigoUpdateDto;
import com.studyhub.sth.application.dtos.tag.TagDto;
import com.studyhub.sth.domain.services.IArtigoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/artigo")
@Tag(name = "Artigos Controller")
public class ArtigoController {

    @Autowired
    private IArtigoService artigoService;

    @GetMapping
    public List<ArtigoDto> listarArtigos(){
        var artigos = this.artigoService.listarArtigos();
        return artigos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtigoDto> buscarArtigoPoId(@PathVariable UUID id){
        var artigo = this.artigoService.buscarArtigoPorId(id);
        return ResponseEntity.ok(artigo);
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<ArtigoDto>> buscarArtigoPorTitulo(@RequestParam @NotNull String titulo){
        var listaArtigos = this.artigoService.buscarArtigoPorTitulo(titulo);
        return ResponseEntity.ok(listaArtigos);
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<ArtigoDto>> buscarArtigoPorUsuario(@PathVariable UUID autorId){
        var listaArtigos = this.artigoService.buscarArtigoPorAutor(autorId);
        return ResponseEntity.ok(listaArtigos);
    }

    @GetMapping("/tags")
    public ResponseEntity<List<ArtigoDto>> buscarArtigoPorTag(@RequestParam @NotNull UUID tagId){
        var listaArtigos = this.artigoService.buscarArtigoPorTag(tagId);
        return ResponseEntity.ok(listaArtigos);
    }

    @PostMapping
    public ResponseEntity<ArtigoDto> criarArtigo(@RequestBody ArtigoCreateDto dto){
        var artigo = this.artigoService.criar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(artigo.getId()).toUri();
        return ResponseEntity.created(uri).body(artigo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ArtigoDto> atualizarArtigo(@PathVariable UUID id, @RequestBody ArtigoUpdateDto dto){
        var artigo = this.artigoService.atualizar(id,dto);
        return ResponseEntity.ok(artigo);
    }

    @PostMapping("/{artigoId}/tags")
    public ResponseEntity<ArtigoDto> adicionarTags(@PathVariable UUID artigoId,@RequestBody List<TagDto> tags) {
        var artigo = artigoService.adicionarTag(artigoId, tags);
        return ResponseEntity.ok(artigo);
    }

    @DeleteMapping("/{artigoId}/tags/{tagId}")
    public ResponseEntity<ArtigoDto> removerTag(@PathVariable UUID artigoId,@PathVariable UUID tagId) {
        var artigo = artigoService.removerTag(artigoId, tagId);
        return ResponseEntity.ok(artigo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarArtigo(@PathVariable UUID id){
        this.artigoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
