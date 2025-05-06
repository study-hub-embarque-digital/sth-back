package com.studyhub.sth.application.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyhub.sth.application.dtos.apresentacoesReunioes.ListApresentacaoReuniaoDto;
import com.studyhub.sth.application.messages.reunioes.*;
import com.studyhub.sth.domain.before.entities.*;
import com.studyhub.sth.domain.before.enums.StatusReuniao;
import com.studyhub.sth.domain.before.repositories.*;
import com.studyhub.sth.domain.before.services.IReuniaoService;
import com.studyhub.sth.infra.redis.entities.ApresentacaoReuniaoCache;
import com.studyhub.sth.infra.redis.entities.ReuniaoCache;
import com.studyhub.sth.infra.redis.repositories.IApresencaoReuniaoCacheRepository;
import com.studyhub.sth.infra.redis.repositories.IReuniaoRedisRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.StreamSupport;

@Service
public class ReuniaoService implements IReuniaoService {
    private final IReuniaoRepository reuniaoRepository;
    private final ISalaTematicaRepository salaTematicaRepository;
    private final IReuniaoRedisRepository reuniaoRedisRepository;
    private final SimpMessageSendingOperations messagingTemplate;
    private final IUsuarioRepository usuarioRepository;
    private final ITopicoRepository topicoRepository;
    private final IApresentacaoReuniaoRepository apresentacaoReuniaoRepository;
    private final IApresencaoReuniaoCacheRepository apresencaoReuniaoCacheRepository;
    private final String wsUsurioTopico = "/topic/user/";
    private final String wsReunioesTopico = "/topic/reunioes/";

    @Autowired
    public ReuniaoService(IReuniaoRepository reuniaoRepository, ISalaTematicaRepository salaTematicaRepository, IReuniaoRedisRepository reuniaoRedisRepository, SimpMessageSendingOperations messagingTemplate, IUsuarioRepository usuarioRepository, ITopicoRepository topicoRepository, IApresentacaoReuniaoRepository apresentacaoReuniaoRepository, IApresencaoReuniaoCacheRepository apresencaoReuniaoCacheRepository) {
        this.reuniaoRepository = reuniaoRepository;
        this.salaTematicaRepository = salaTematicaRepository;
        this.reuniaoRedisRepository = reuniaoRedisRepository;
        this.messagingTemplate = messagingTemplate;
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
        this.apresentacaoReuniaoRepository = apresentacaoReuniaoRepository;
        this.apresencaoReuniaoCacheRepository = apresencaoReuniaoCacheRepository;
    }

    public void entrarReuniaoSalaTematica(UUID salaTematicaId, UUID usuarioAtual) {
        Usuario usuarioAtualPeloBanco = this.usuarioRepository.findById(usuarioAtual).orElseThrow(() -> new RuntimeException("asd"));
        List<Reuniao> reunioesDisponiveis = this.reuniaoRepository.findAvailableReunioes(salaTematicaId);
        Optional<Reuniao> reuniaoDisponivel = reunioesDisponiveis.stream().findFirst();


        if (reuniaoDisponivel.isPresent()) {
            reuniaoDisponivel.get().addParticipante(usuarioAtualPeloBanco);
            this.reuniaoRepository.save(reuniaoDisponivel.get());
            this.reuniaoRedisRepository.save(new ReuniaoCache(reuniaoDisponivel.get()));
            this.messagingTemplate.convertAndSend(wsUsurioTopico + usuarioAtual.toString(), new ReuniaoEncontradaMensagem(reuniaoDisponivel.get().getReuniaoId(), reuniaoDisponivel.get().getStatus()));

            if (reuniaoDisponivel.get().getStatus() == StatusReuniao.AGUARDANDO_PARTICIPANTES) {
                this.messagingTemplate.convertAndSend(wsReunioesTopico + reuniaoDisponivel.get().getReuniaoId().toString(), new AguardandoMaisParticipantes(reuniaoDisponivel.get().getReuniaoId(), reuniaoDisponivel.get().getStatus()));
            }

            return;
        }

        SalaTematica salaTematica = this.salaTematicaRepository.findById(salaTematicaId).orElseThrow(() -> new RuntimeException("Sala temática não encontrada"));

        Reuniao novaReuniao = new Reuniao(salaTematica, usuarioAtualPeloBanco);

        this.reuniaoRepository.save(novaReuniao);
        this.reuniaoRedisRepository.save(new ReuniaoCache(novaReuniao));
        this.messagingTemplate.convertAndSend(wsUsurioTopico + usuarioAtual.toString(), new ReuniaoEncontradaMensagem(novaReuniao.getReuniaoId(), novaReuniao.getStatus()));
    }

    public void cancelarReunioes(List<UUID> reunioesParaCancelarIds) {
        List<Reuniao> reunioes = this.reuniaoRepository.findAllById(reunioesParaCancelarIds);

        if (reunioes.isEmpty()) return;

        for (Reuniao reuniao : reunioes) {
            reuniao.cancelar();
            this.reuniaoRepository.save(reuniao);
            this.messagingTemplate.convertAndSend(wsReunioesTopico + reuniao.getReuniaoId(), new ReuniaoCanceladaMensagem(reuniao.getReuniaoId(), reuniao.getStatus()));
        }

        this.reuniaoRedisRepository.deleteAllById(reunioesParaCancelarIds);
    }

    @Transactional
    public void iniciarReunioes(List<UUID> reunioesParaIniciarIds) {
        List<Reuniao> reunioesParaIniciar = this.reuniaoRepository.findAllById(reunioesParaIniciarIds);

        for (Reuniao reuniao : reunioesParaIniciar) {
            reuniao.iniciarEstudo();
            this.separarTopicos(reuniao.getParticipantes(), reuniao.getSalaTematica().getTopico().getTopicoId(), reuniao);
            this.reuniaoRepository.save(reuniao);
            this.reuniaoRedisRepository.save(new ReuniaoCache(reuniao));
            this.messagingTemplate.convertAndSend(wsReunioesTopico + reuniao.getReuniaoId(), new TempoEstudoIniciouMensagem(reuniao.getReuniaoId(), reuniao.getStatus()));
        }
    }

    @Transactional
    public void iniciarApresentacoes(List<UUID> reunioesParaIniciarApresentacoesIds) throws JsonProcessingException {
        List<Reuniao> reunioesParaIniciarApresentacoes = this.reuniaoRepository.findAllById(reunioesParaIniciarApresentacoesIds);

        for (Reuniao reuniaoParaIniciarApresentacao : reunioesParaIniciarApresentacoes) {
            reuniaoParaIniciarApresentacao.iniciarApresentacoes();

            List<ApresentacaoReuniao> apresentacoesReuniao = this.apresentacaoReuniaoRepository.findApresentacaoReuniaoByReuniaoReuniaoId(reuniaoParaIniciarApresentacao.getReuniaoId());
            ApresentacaoReuniao apresentacaoReuniaoInicial = apresentacoesReuniao.stream().filter(apresentacaoReuniao -> apresentacaoReuniao.getOrdem() == 0).findFirst().orElseThrow(() -> new RuntimeException("as"));
            List<ListApresentacaoReuniaoDto> apresentacoesDtos = apresentacoesReuniao.stream().map(ListApresentacaoReuniaoDto::new).toList();
            List<ApresentacaoReuniaoCache> cacheApresentacoes = apresentacoesReuniao.stream().map(ApresentacaoReuniaoCache::new).toList();

            this.apresencaoReuniaoCacheRepository.saveAll(cacheApresentacoes);

            IniciaApresentacoesMensagem iniciaApresentacoesMensagem = new IniciaApresentacoesMensagem(reuniaoParaIniciarApresentacao.getReuniaoId(), reuniaoParaIniciarApresentacao.getStatus(), apresentacaoReuniaoInicial.getOrdem(), apresentacoesDtos);
            ObjectMapper objectMapper = new ObjectMapper();
            String iniciaApresentacaoMensagemAsString = objectMapper.writeValueAsString(iniciaApresentacoesMensagem);
            apresentacaoReuniaoInicial.iniciar();

            this.apresentacaoReuniaoRepository.save(apresentacaoReuniaoInicial);
            this.apresencaoReuniaoCacheRepository.save(new ApresentacaoReuniaoCache(apresentacaoReuniaoInicial));

            this.reuniaoRepository.save(reuniaoParaIniciarApresentacao);
            this.reuniaoRedisRepository.save(new ReuniaoCache(reuniaoParaIniciarApresentacao));

            this.messagingTemplate.convertAndSend(wsUsurioTopico + apresentacaoReuniaoInicial.getUsuario().getUsuarioId(), new AvisarUsuarioSuaVezApresentarMensagem());
            this.messagingTemplate.convertAndSend(wsReunioesTopico + reuniaoParaIniciarApresentacao.getReuniaoId(), iniciaApresentacaoMensagemAsString);
        }
    }

    @Transactional
    public void finalizarApresentacaoIniciarDiscussao(List<UUID> apresentacoesParaFinalizarIds) {
        List<ApresentacaoReuniao> apresentacoesReuniao = this.apresentacaoReuniaoRepository.findAllById(apresentacoesParaFinalizarIds);

        for (ApresentacaoReuniao apresentacaoParaFinalizar : apresentacoesReuniao) {
            apresentacaoParaFinalizar.finalizar();
            apresentacaoParaFinalizar.iniciarDiscussao();
            this.apresentacaoReuniaoRepository.save(apresentacaoParaFinalizar);
            this.apresencaoReuniaoCacheRepository.save(new ApresentacaoReuniaoCache(apresentacaoParaFinalizar));
            this.messagingTemplate.convertAndSend(wsReunioesTopico + apresentacaoParaFinalizar.getReuniao().getReuniaoId(), new AvisarInicioDiscussaoMensagem(apresentacaoParaFinalizar.getReuniao().getReuniaoId(), apresentacaoParaFinalizar.getReuniao().getStatus(), apresentacaoParaFinalizar.getTopicos().stream().map(Topico::getTitulo).toList()));  // notifica que é o proximo a começar
            this.apresentacaoReuniaoRepository.save(apresentacaoParaFinalizar);
        }
    }

    @Transactional
    public void finalizarTempoDeDiscussao(List<UUID> apresentacoesParaFinalizarTempoDeDiscussaoIds) {
        List<ApresentacaoReuniao> apresentacoesReuniao = this.apresentacaoReuniaoRepository.findAllById(apresentacoesParaFinalizarTempoDeDiscussaoIds);

        for (ApresentacaoReuniao apresentacaoParaFinalizar : apresentacoesReuniao) {
            apresentacaoParaFinalizar.finalizarDiscussao();
            this.apresentacaoReuniaoRepository.save(apresentacaoParaFinalizar);
            this.apresencaoReuniaoCacheRepository.save(new ApresentacaoReuniaoCache(apresentacaoParaFinalizar));

            this.messagingTemplate.convertAndSend(wsReunioesTopico + apresentacaoParaFinalizar.getReuniao().getReuniaoId(), new AvisarFimDiscussaoMensagem(apresentacaoParaFinalizar.getReuniao().getReuniaoId(), apresentacaoParaFinalizar.getReuniao().getStatus()));  // notifica que é o proximo a começar

            Optional<UUID> proximaApresentacao = buscarProximaApresentacaoCache(apresentacaoParaFinalizar.getOrdem(), apresentacaoParaFinalizar.getReuniao().getReuniaoId());

            if (proximaApresentacao.isEmpty()) {
                this.finalizarReuniao(apresentacaoParaFinalizar.getReuniao().getReuniaoId());
                return;
            }

            this.iniciarProximaApresentacao(proximaApresentacao.get());
        }
    }

    private void separarTopicos(List<Usuario> participantes, UUID topicoId, Reuniao reuniao) {
        List<Topico> topicosParaSeparar = this.topicoRepository.findSubtopicos(topicoId);
        int ordem = 0;

        for (Usuario participante : participantes) {
            ApresentacaoReuniao apresentacaoReuniao = new ApresentacaoReuniao(ordem, participante, reuniao);
            Random random = new Random();
            int posicaoTopico = random.nextInt(topicosParaSeparar.size() - 1);
            Topico topico = topicosParaSeparar.get(posicaoTopico);
            apresentacaoReuniao.addTopico(topico);
            topicosParaSeparar.remove(posicaoTopico);
            this.apresentacaoReuniaoRepository.save(apresentacaoReuniao);
            this.messagingTemplate.convertAndSend(wsUsurioTopico + participante.getUsuarioId(), new TopicoSeparadoMensagem(Arrays.stream(new String[] {topico.getTitulo()}).toList()));
            ordem++;
        }
    }

    private void iniciarProximaApresentacao(UUID proximaApresentacaoId) {
        ApresentacaoReuniao apresentacaoReuniaoParaIniciar = this.apresentacaoReuniaoRepository.findById(proximaApresentacaoId).orElseThrow(() -> new RuntimeException("asdasd"));
        apresentacaoReuniaoParaIniciar.iniciar();

        this.apresentacaoReuniaoRepository.save(apresentacaoReuniaoParaIniciar);
        this.apresencaoReuniaoCacheRepository.save(new ApresentacaoReuniaoCache(apresentacaoReuniaoParaIniciar));

        this.messagingTemplate.convertAndSend(wsUsurioTopico + apresentacaoReuniaoParaIniciar.getUsuario().getUsuarioId(), new AvisarUsuarioSuaVezApresentarMensagem());
        this.messagingTemplate.convertAndSend(wsReunioesTopico + apresentacaoReuniaoParaIniciar.getReuniao().getReuniaoId(), new IniciaApresentacaoMensagem(apresentacaoReuniaoParaIniciar.getReuniao().getReuniaoId(), apresentacaoReuniaoParaIniciar.getReuniao().getStatus(), apresentacaoReuniaoParaIniciar.getOrdem()));
    }


    private Optional<UUID> buscarProximaApresentacaoCache(int apresentacaoAtual, UUID reuniaoId) {
        int ordemParaBusca = apresentacaoAtual + 1;
        Optional<ApresentacaoReuniaoCache> proximaApresentacao =  StreamSupport.stream(apresencaoReuniaoCacheRepository.findAll().spliterator(), false)
                .filter(apresentacao -> apresentacao != null && apresentacao.getReuniaoId().equals(reuniaoId) && apresentacao.getOrdem() == ordemParaBusca)
                .toList()
                .stream().findFirst();

        return proximaApresentacao.map(ApresentacaoReuniaoCache::getApresentacaoReuniaoId);
    }

    private void finalizarReuniao(UUID reuniaoId) {
        Reuniao reuniao = this.reuniaoRepository.findById(reuniaoId).orElseThrow(() -> new RuntimeException("adfcasd"));
        reuniao.encerrar();

        this.reuniaoRepository.save(reuniao);
        this.reuniaoRedisRepository.deleteById(reuniao.getReuniaoId());
        this.messagingTemplate.convertAndSend(wsReunioesTopico + reuniao.getReuniaoId(), new FinalizarReuniaoMensagem(reuniao.getReuniaoId(), reuniao.getStatus()));
    }
}
