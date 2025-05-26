package com.studyhub.sth.application.dtos.reunioes;

import com.studyhub.sth.domain.entities.Reuniao;
import com.studyhub.sth.domain.enums.StatusReuniao;

import com.studyhub.sth.libs.core.BaseWSResponseMessage;
import com.studyhub.sth.libs.core.WSMessageContext;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ReuniaoDto extends BaseWSResponseMessage {
    private UUID reuniaoId;
    private Date criadoEm;
    private StatusReuniao status;
    private String topicoEstudo;

    public ReuniaoDto(UUID reuniaoId, Date criadoEm, StatusReuniao status) {
        this.reuniaoId = reuniaoId;
        this.criadoEm = criadoEm;
        this.status = status;
    }
//
//    public ReuniaoDto(Reuniao reuniao) {
//        super(WSMessageContext.REUNIOES, );
//        this.reuniaoId = reuniao.getReuniaoId();
//        this.criadoEm = reuniao.getCriadoEm();
//        this.status = reuniao.getStatus();
//    }
//
//    public ReuniaoDto(Reuniao reuniao, String topico) {
//        super(WSMessageContext.REUNIOES, "");
//        this.reuniaoId = reuniao.getReuniaoId();
//        this.criadoEm = reuniao.getCriadoEm();
//        this.status = reuniao.getStatus();
//        this.setTopicoEstudo(topico);
//    }
}