package com.studyhub.sth.api.controllers;

import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IUsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Controller")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping(value = "{id}/foto", consumes = "multipart/form-data")
    public ResponseEntity<String> atualizarFoto(
            @PathVariable UUID id,
            @RequestParam("foto") MultipartFile foto) throws ElementoNaoEncontradoExcecao {
        if (foto == null || foto.isEmpty()) {
            return ResponseEntity.badRequest().body("O arquivo de foto não foi enviado.");
        }
        try {
            String fotoNova = usuarioService.atualizarFoto(id, foto);
            return ResponseEntity.ok(fotoNova);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Erro ao enviar o arquivo: " + e.getMessage());
        }
    }


}
