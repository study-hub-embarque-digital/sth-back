package com.studyhub.sth.controllers;

import com.studyhub.sth.dtos.squad.SquadCreateDTO;
import com.studyhub.sth.dtos.squad.SquadDTO;
import com.studyhub.sth.services.squad.ISquadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/squads")
public class SquadController {

    @Autowired
    private ISquadService squadService;

    @GetMapping
    public ResponseEntity<List<SquadDTO>> getAllSquads() {
        List<SquadDTO> squads = squadService.findAll();
        return ResponseEntity.ok(squads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SquadDTO> getSquadById(@PathVariable UUID id) {
        Optional<SquadDTO> squad = squadService.findById(id);
        return squad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SquadDTO> createSquad(@RequestBody SquadCreateDTO squadCreateDTO) {
        SquadDTO newSquad = squadService.save(squadCreateDTO);
        return ResponseEntity.ok(newSquad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SquadDTO> updateSquad(@PathVariable UUID id, @RequestBody SquadUpdateDTO squadUpdateDTO) {
        Optional<SquadDTO> updatedSquad = squadService.update(id, squadUpdateDTO);
        return updatedSquad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSquad(@PathVariable UUID id) {
        squadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

