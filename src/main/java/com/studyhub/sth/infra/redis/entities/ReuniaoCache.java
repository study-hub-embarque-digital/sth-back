package com.studyhub.sth.infra.redis.entities;

import com.studyhub.sth.domain.before.entities.Reuniao;
import com.studyhub.sth.domain.before.enums.StatusReuniao;
import com.studyhub.sth.domain.before.enums.TipoReuniao;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@RedisHash("reunioes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReuniaoCache implements Serializable {
    @Id
    private UUID reuniaoId;
    private Date criadoEm;
    private StatusReuniao status;
    private TipoReuniao tipo;
    private Date statusAtualizadoEm;

    public ReuniaoCache(Reuniao reuniao) {
        this.reuniaoId = reuniao.getReuniaoId();
        this.criadoEm = reuniao.getCriadoEm();
        this.status = reuniao.getStatus();
        this.tipo = reuniao.getTipo();
        this.statusAtualizadoEm = reuniao.getStatusAtualizadoEm();
    }
}
