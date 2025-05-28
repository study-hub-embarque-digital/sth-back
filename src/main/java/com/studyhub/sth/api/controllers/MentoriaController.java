package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.mentoria.MentoriaCreateDto;
import com.studyhub.sth.application.dtos.mentoria.MentoriaCreateWithEncontrosDto;
import com.studyhub.sth.application.dtos.mentoria.MentoriaDto;
import com.studyhub.sth.application.dtos.mentoria.MentoriaUpdateDto;
import com.studyhub.sth.domain.services.IMentoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mentoria")
public class MentoriaController {

    @Autowired
    private IMentoriaService mentoriaService;

    @PostMapping
    public ResponseEntity<MentoriaDto> save(@RequestBody MentoriaCreateDto dto){
        var mentoria = this.mentoriaService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mentoria.getId()).toUri();
        return ResponseEntity.created(uri).body(mentoria);
    }

    @PostMapping("/mentoria-encontros")
    public ResponseEntity<MentoriaDto> saveWithEcontros(@RequestBody MentoriaCreateWithEncontrosDto dto){
        var mentoria = this.mentoriaService.saveWithEncontros(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mentoria.getId()).toUri();
        return ResponseEntity.created(uri).body(mentoria);
    }

    @GetMapping
    public ResponseEntity<List<MentoriaDto>> getAll(){
        var lista = this.mentoriaService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentoriaDto> findById(@PathVariable UUID id){
        var mentoria = this.mentoriaService.findById(id);
        return ResponseEntity.ok(mentoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MentoriaDto> update(@PathVariable UUID id, @RequestBody MentoriaUpdateDto dto){
        var mentoria = this.mentoriaService.update(id,dto);
        return ResponseEntity.ok(mentoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        this.mentoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
