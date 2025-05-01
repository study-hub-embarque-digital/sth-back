package com.studyhub.sth.jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studyhub.sth.domain.enums.StatusReuniao;
import com.studyhub.sth.domain.services.IReuniaoService;
import com.studyhub.sth.infra.redis.entities.ApresentacaoReuniaoCache;
import com.studyhub.sth.infra.redis.entities.ReuniaoCache;
import com.studyhub.sth.infra.redis.repositories.IApresencaoReuniaoCacheRepository;
import com.studyhub.sth.infra.redis.repositories.IReuniaoRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Slf4j
@Component
public class ReunioesJobs {
    @Autowired
    private IReuniaoRedisRepository reuniaoRedisRepository;
    @Autowired
    private IApresencaoReuniaoCacheRepository apresencaoReuniaoCacheRepository;
    @Autowired
    private IReuniaoService reuniaoService;

//    @Scheduled(fixedDelay = 15000L)
//    public void cancelarReunioesEmEspera() {
//        log.info("JOB [cancelarReunioesEmEspera] - INICIOU");
//        Instant cincoMinAtras = Instant.now().minusSeconds(15);// Instant cincoMinAtras = Instant.now().minus(2, ChronoUnit.MINUTES);
//
//        List<ReuniaoCache> reunioesParaCancelar = StreamSupport.stream(reuniaoRedisRepository.findAll().spliterator(), false)
//                .filter(reuniao -> reuniao != null && reuniao.getCriadoEm().toInstant().isBefore(cincoMinAtras) && reuniao.getStatus() == StatusReuniao.EM_ESPERA)
//                .toList();
//
//        if (reunioesParaCancelar.isEmpty()) return;
//
//        List<UUID> uuids = reunioesParaCancelar.stream().map(ReuniaoCache::getReuniaoId).toList();
//
//        this.reuniaoService.cancelarReunioes(uuids);
//    }
//
//    @Scheduled(fixedDelay = 15000L)
//    public void reunioesParaIniciar() {
//        Instant cincoMinAtras = Instant.now().minusSeconds(15);// Instant cincoMinAtras = Instant.now().minus(2, ChronoUnit.MINUTES);
//
//        List<ReuniaoCache> reunioesParaIniciar = StreamSupport.stream(reuniaoRedisRepository.findAll().spliterator(), false)
//                .filter(reuniao -> reuniao != null && reuniao.getStatusAtualizadoEm().toInstant().isBefore(cincoMinAtras) && reuniao.getStatus() == StatusReuniao.AGUARDANDO_PARTICIPANTES)
//                .toList();
//
//        if (reunioesParaIniciar.isEmpty()) return;
//
//        List<UUID> uuids = reunioesParaIniciar.stream().map(ReuniaoCache::getReuniaoId).toList();
//
//        this.reuniaoService.iniciarReunioes(uuids);
//    }
//
//    @Scheduled(fixedDelay = 15000L)
//    public void reunioesParaIniciarApresentacoes() throws JsonProcessingException {
//        Instant cincoMinAtras = Instant.now().minusSeconds(15);// Instant cincoMinAtras = Instant.now().minus(2, ChronoUnit.MINUTES);
//
//        List<ReuniaoCache> reunioesParaIniciarApresentacoes = StreamSupport.stream(reuniaoRedisRepository.findAll().spliterator(), false)
//                .filter(reuniao -> reuniao != null && reuniao.getStatusAtualizadoEm().toInstant().isBefore(cincoMinAtras) && reuniao.getStatus() == StatusReuniao.EM_ESTUDO)
//                .toList();
//
//        if (reunioesParaIniciarApresentacoes.isEmpty()) return;
//
//        List<UUID> reunioesParaIniciarApresentacoesIds = reunioesParaIniciarApresentacoes.stream().map(ReuniaoCache::getReuniaoId).toList();
//
//        this.reuniaoService.iniciarApresentacoes(reunioesParaIniciarApresentacoesIds);
//    }
//
//    @Scheduled(fixedDelay = 15000L)
//    public void iniciarDiscussoes() throws JsonProcessingException {
//        Instant cincoMinAtras = Instant.now().minusSeconds(15);
//
//        List<ApresentacaoReuniaoCache> aporesentacoesParaIniciarDiscussao =  StreamSupport.stream(apresencaoReuniaoCacheRepository.findAll().spliterator(), false)
//                .filter(apresentacao -> apresentacao != null && apresentacao.getHoraInicio() != null && apresentacao.getHoraInicio().toInstant().isBefore(cincoMinAtras) && !apresentacao.isFinalizada())
//                .toList();
//
//        if (aporesentacoesParaIniciarDiscussao.isEmpty()) return;
//
//        List<UUID> aporesentacoesParaIniciarDiscussaoIds = aporesentacoesParaIniciarDiscussao.stream().map(ApresentacaoReuniaoCache::getApresentacaoReuniaoId).toList();
//
//        this.reuniaoService.finalizarApresentacaoIniciarDiscussao(aporesentacoesParaIniciarDiscussaoIds);
//    }
//
//    @Scheduled(fixedDelay = 15000L)
//    public void finalizarDiscussoesEIniciarProximaCasoTenha() throws JsonProcessingException {
//        Instant cincoMinAtras = Instant.now().minusSeconds(15);
//
//        List<ApresentacaoReuniaoCache> aporesentacoesParaFinalizarDiscussao =  StreamSupport.stream(apresencaoReuniaoCacheRepository.findAll().spliterator(), false)
//                .filter(apresentacao -> apresentacao != null && apresentacao.getInicioDiscussao() != null && apresentacao.getInicioDiscussao().toInstant().isBefore(cincoMinAtras) && !apresentacao.isDiscussaoFinalizada())
//                .toList();
//
//        if (aporesentacoesParaFinalizarDiscussao.isEmpty()) return;
//
//        List<UUID> reunioesParaIniciarApresentacoesIds = aporesentacoesParaFinalizarDiscussao.stream().map(ApresentacaoReuniaoCache::getApresentacaoReuniaoId).toList();
//
//        this.reuniaoService.finalizarTempoDeDiscussao(reunioesParaIniciarApresentacoesIds);
//    }
}
