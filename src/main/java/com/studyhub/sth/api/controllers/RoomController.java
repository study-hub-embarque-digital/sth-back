package com.studyhub.sth.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studyhub.sth.application.dtos.rooms.GeneratedRoomDto;
import com.studyhub.sth.application.dtos.rooms.RoomCreateDto;
import com.studyhub.sth.application.dtos.rooms.RoomUpdateDto;
import com.studyhub.sth.application.dtos.rooms.RoomDto;
import com.studyhub.sth.application.dtos.salasTematica.SalaTematicaDto;
import com.studyhub.sth.domain.entities.SalaTematica;
import com.studyhub.sth.domain.enums.Dificuldade;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.repositories.ISalaTematicaRepository;
import com.studyhub.sth.domain.services.IRoomService;
import com.studyhub.sth.domain.services.ISalasTematicaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
@Tag(name = "Rooms Controller")
public class RoomController {
    @Autowired
    private IRoomService roomService;
    @Autowired
    private ISalasTematicaService salasTematicaService;

    @PostMapping
    @Transactional
    public ResponseEntity<RoomDto> criar(@RequestBody RoomCreateDto novoRoomDto) {
        return new ResponseEntity<>(this.roomService.criar(novoRoomDto), HttpStatus.CREATED);
    }

    @PutMapping("/{roomId}")
    @Transactional
    public ResponseEntity<RoomDto> atualizar(@PathVariable UUID roomId, @RequestBody RoomUpdateDto roomAtualizadaDto) throws ElementoNaoEncontradoExcecao {
        return new ResponseEntity<>(this.roomService.atualizar(roomId, roomAtualizadaDto), HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> detalhar(@PathVariable UUID roomId) throws ElementoNaoEncontradoExcecao {
        return new ResponseEntity<>(this.roomService.detalhar(roomId), HttpStatus.OK);
    }

    @GetMapping("/{roomId}/salas")
    public ResponseEntity<List<SalaTematicaDto>> salas(@PathVariable UUID roomId, @RequestParam Dificuldade dificuldade) {
        return new ResponseEntity<>(this.salasTematicaService.obterPorRoom(roomId, dificuldade), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> listar() {
        return new ResponseEntity<>(this.roomService.listar(), HttpStatus.OK);
    }

    @GetMapping("/generate")
    public ResponseEntity<GeneratedRoomDto> gerarRoom() throws IOException {
        return new ResponseEntity<>(this.roomService.generateRoom(), HttpStatus.OK);
    }

    @GetMapping("/teste")
    public ResponseEntity teste() throws IOException {
        this.roomService.teste();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity deletar(@PathVariable UUID roomId) throws ElementoNaoEncontradoExcecao {
        this.roomService.deletar(roomId);
        return ResponseEntity.noContent().build();
    }

}
