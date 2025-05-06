package com.studyhub.sth.api.controllers;

import com.studyhub.sth.domain.before.services.IReuniaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

