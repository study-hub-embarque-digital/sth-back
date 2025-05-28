package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.tag.TagCreateAndUpdateDTO;
import com.studyhub.sth.application.dtos.tag.TagDto;
import com.studyhub.sth.domain.services.ITagService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
@Tag(name = "Tags Controller")
public class TagController {

    @Autowired
    private ITagService tagService;

    @PostMapping
    public ResponseEntity<TagDto> criarTag(@RequestBody TagCreateAndUpdateDTO dto){
        var tag = this.tagService.criar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tag.getId()).toUri();
        return ResponseEntity.created(uri).body(tag);
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> listarTags(){
        var lista = this.tagService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> buscarTagPorId(@PathVariable UUID id){
        var tag = this.tagService.buscarPorId(id);
        return ResponseEntity.ok(tag);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<TagDto> buscarTagPorNome(@PathVariable String nome){
        var tag = this.tagService.buscarPorNome(nome);
        return ResponseEntity.ok(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarTag(@PathVariable UUID id){
        this.tagService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
