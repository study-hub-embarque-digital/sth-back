package com.studyhub.sth.domain.before.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.UUID;

public interface IReuniaoService {
    void entrarReuniaoSalaTematica(UUID salaTematicaId, UUID usuarioAtual);
    void cancelarReunioes(List<UUID> reunioesParaCancelarIds);
    void iniciarReunioes(List<UUID> reunioesParaIniciar);
    void iniciarApresentacoes(List<UUID> reunioesParaIniciarApresentacoesIds) throws JsonProcessingException;
    void finalizarApresentacaoIniciarDiscussao(List<UUID> apresentacoesParaFinalizarIds);
    void finalizarTempoDeDiscussao(List<UUID> apresentacoesParaFinalizarTempoDeDiscussaoIds);
}
