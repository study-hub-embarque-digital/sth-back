package com.studyhub.sth.application.services;

import com.studyhub.sth.domain.annotations.CurrentUser;
import com.studyhub.sth.domain.entities.Reuniao;
import com.studyhub.sth.domain.entities.SalaTematica;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.repositories.IReuniaoRepository;
import com.studyhub.sth.domain.repositories.ISalaTematicaRepository;
import com.studyhub.sth.domain.services.IReuniaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReuniaoService implements IReuniaoService {
    private IReuniaoRepository reuniaoRepository;
    private ISalaTematicaRepository salaTematicaRepository;

    @Autowired
    public ReuniaoService(IReuniaoRepository reuniaoRepository, ISalaTematicaRepository salaTematicaRepository) {
        this.reuniaoRepository = reuniaoRepository;
        this.salaTematicaRepository = salaTematicaRepository;
    }

    public UUID entrarReuniaoSalaTematica(UUID salaTematicaId, Usuario usuarioAtual) {
        Optional<Reuniao> reuniao = this.reuniaoRepository.findBySalaTematicaSalaTematicaId(salaTematicaId);

        if (reuniao.isPresent()) {
            return reuniao.get().getReuniaoId();
        }

        SalaTematica salaTematica = this.salaTematicaRepository.findById(salaTematicaId).orElseThrow(() -> new RuntimeException("Sala temática não encontrada"));

        Reuniao novaReuniao = new Reuniao(salaTematica, usuarioAtual);

        this.reuniaoRepository.save(novaReuniao);

        return novaReuniao.getReuniaoId();
    }

    public void sairReuniao() {}

    public void agendarReuniao() {}
}
