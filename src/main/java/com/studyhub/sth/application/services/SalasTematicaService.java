package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.salasTematica.SalaTematicaDto;
import com.studyhub.sth.domain.before.entities.SalaTematica;
import com.studyhub.sth.domain.before.enums.Dificuldade;
import com.studyhub.sth.domain.before.repositories.ISalaTematicaRepository;
import com.studyhub.sth.domain.before.services.ISalasTematicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SalasTematicaService implements ISalasTematicaService {
    private final ISalaTematicaRepository salaTematicaRepository;

    @Autowired
    public SalasTematicaService(ISalaTematicaRepository salaTematicaRepository) {
        this.salaTematicaRepository = salaTematicaRepository;
    }

    public List<SalaTematicaDto> obterPorRoom(UUID roomId, Dificuldade dificuldade) {
        List<SalaTematica> salasTematica = this.salaTematicaRepository.findByRoomAndDifficulty(roomId, dificuldade);

        return salasTematica.stream().map(SalaTematicaDto::new).toList();
    }
}
