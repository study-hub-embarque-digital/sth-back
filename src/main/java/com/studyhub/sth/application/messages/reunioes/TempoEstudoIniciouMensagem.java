package com.studyhub.sth.application.messages.reunioes;

import com.studyhub.sth.domain.before.enums.StatusReuniao;
import com.studyhub.sth.libs.core.BaseWSResponseMessage;
import com.studyhub.sth.libs.core.WSMessageContext;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TempoEstudoIniciouMensagem extends BaseWSResponseMessage<ReunioesMensagens> {
    private UUID reuniaoId;
    private StatusReuniao status;

    public TempoEstudoIniciouMensagem(UUID reuniaoId, StatusReuniao status) {
        super(WSMessageContext.REUNIOES, ReunioesMensagens.TEMPO_ESTUDO_INICIOU);
        this.reuniaoId = reuniaoId;
        this.status = status;
    }
}
