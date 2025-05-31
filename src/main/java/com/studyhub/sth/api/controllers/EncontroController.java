package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.encontro.EncontroCreateAndUpdateDto;
import com.studyhub.sth.application.dtos.encontro.EncontroDto;
import com.studyhub.sth.domain.services.IEcontroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/encontro")
public class EncontroController {

    @Autowired
    private IEcontroService encontroService;

    @PostMapping("/{mentoriaId}")
    public ResponseEntity<EncontroDto> createEncontro(
            @PathVariable UUID mentoriaId,
            @RequestBody @Valid EncontroCreateAndUpdateDto dto) {
        var encontro = encontroService.save(dto, mentoriaId);
        return ResponseEntity.ok(encontro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EncontroDto> updateEncontro(
            @PathVariable UUID id,
            @RequestBody @Valid EncontroCreateAndUpdateDto dto) {
        var encontroAtualizado = encontroService.update(id, dto);
        return ResponseEntity.ok(encontroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEncontro(@PathVariable UUID id) {
        encontroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/gerar-lista/{mentoriaId}")
    public ResponseEntity<List<EncontroDto>> gerarListaEncontros(@PathVariable UUID mentoriaId) {
        // Aqui você pode considerar mover a lógica para que o service aceite apenas o ID da mentoria,
        // mas como ele espera o objeto Mentoria, é necessário buscar no repositório.
        throw new UnsupportedOperationException("Esse endpoint requer a mentoria como objeto. Você pode alterar a service para aceitar o ID ou mover esse método para MentoriaController.");
    }

    @GetMapping("/mentoria/{mentoriaId}")
    public ResponseEntity<List<EncontroDto>> listarEncontrosDaMentoria(@PathVariable UUID mentoriaId) {
        List<EncontroDto> encontros = encontroService.listarPorMentoriaId(mentoriaId);
        return ResponseEntity.ok(encontros);
    }

}
