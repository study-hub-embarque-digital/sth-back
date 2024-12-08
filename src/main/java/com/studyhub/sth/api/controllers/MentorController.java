package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.mentor.MentorUpdateDto;
import com.studyhub.sth.application.dtos.mentor.MentorDto;
import com.studyhub.sth.application.dtos.mentor.MentorCreateDto;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IMentorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mentor")
@Tag(name = "Mentores Controller")
public class MentorController {
    @Autowired
    IMentorService mentorService;

    @PostMapping
    @Transactional
    public ResponseEntity<MentorDto> criarMentor(@RequestBody MentorCreateDto dto) {
        var mentor = this.mentorService.criar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mentor.getId()).toUri();
        return ResponseEntity.created(uri).body(mentor);
    }

    @GetMapping
    public ResponseEntity<List<MentorDto>> listarMentores() {
        var mentores = this.mentorService.listar();
        return ResponseEntity.ok(mentores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentorDto> buscarMentorPorID(@PathVariable UUID id) throws ElementoNaoEncontradoExcecao {
        var mentor = this.mentorService.buscarPorId(id);
        return ResponseEntity.ok(mentor);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarMentor(@PathVariable UUID id) throws ElementoNaoEncontradoExcecao {
        this.mentorService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MentorDto> atualizarMentor(@PathVariable UUID id, @RequestBody MentorUpdateDto dto) throws ElementoNaoEncontradoExcecao {
        var mentor = this.mentorService.atualizar(id, dto);
        return ResponseEntity.ok(mentor);
    }


    @GetMapping("/squads/{id}")
    public  ResponseEntity<List<SquadDTO>> listarSquadsDeMentor(@PathVariable UUID id){
        var squads = this.mentorService.listarSquads(id);
        return ResponseEntity.ok(squads);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<MentorDto> buscarMentorPorNome(@PathVariable String nome) throws ElementoNaoEncontradoExcecao {
        var mentor = this.mentorService.buscarPorNome(nome);
        return ResponseEntity.ok(mentor);
    }

}
