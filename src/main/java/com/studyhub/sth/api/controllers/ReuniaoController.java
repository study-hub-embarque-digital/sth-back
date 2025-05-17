package com.studyhub.sth.api.controllers;

import com.studyhub.sth.domain.annotations.CurrentUser;
import com.studyhub.sth.domain.entities.Reuniao;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.services.IReuniaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reunioes")
@Tag(name = "Reunioes Controller")
public class ReuniaoController {
    @Autowired
    IReuniaoService reuniaoService;


//    @PostMapping("/reuniao")
//    @Operation(summary = "Entra em uma reunião já existente da sala, ou cria caso nao exista.")
//    public ResponseEntity<UUID> CriaReuniao(@RequestBody UUID salaTematicaId, @CurrentUser Usuario usuarioAtual) {
//        return ResponseEntity.ok(reuniaoService.entrarReuniaoSalaTematica(salaTematicaId, usuarioAtual));
//    }
}

