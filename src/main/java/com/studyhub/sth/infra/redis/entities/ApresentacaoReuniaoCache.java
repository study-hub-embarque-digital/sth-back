package com.studyhub.sth.infra.redis.entities;

import com.studyhub.sth.domain.entities.ApresentacaoReuniao;
import com.studyhub.sth.domain.entities.Topico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RedisHash("apresentacoes_reunioes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApresentacaoReuniaoCache {
    @Id
    private UUID apresentacaoReuniaoId;
    private UUID reuniaoId;
    private UUID usuarioId;
    private int ordem;
    private Date horaInicio;
    private boolean finalizada;
    private Date inicioDiscussao;
    private boolean discussaoFinalizada;
    private List<String> topicos;

    public ApresentacaoReuniaoCache(ApresentacaoReuniao apresentacaoReuniao) {
        this.apresentacaoReuniaoId = apresentacaoReuniao.getApresentacaoReuniaoId();
        this.reuniaoId = apresentacaoReuniao.getReuniao().getReuniaoId();
        this.usuarioId = apresentacaoReuniao.getUsuario().getUsuarioId();
        this.ordem = apresentacaoReuniao.getOrdem();
        this.horaInicio = apresentacaoReuniao.getHoraInicio();
        this.finalizada = apresentacaoReuniao.isFinalizada();
        this.inicioDiscussao = apresentacaoReuniao.getInicioDiscussao();
        this.discussaoFinalizada = apresentacaoReuniao.isDiscussaoFinalizada();
        this.topicos = apresentacaoReuniao.getTopicos().stream().map(Topico::getTitulo).toList();
    }
}
