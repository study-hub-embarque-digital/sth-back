package com.studyhub.sth.application.messages.reunioes;

import com.studyhub.sth.domain.before.enums.StatusReuniao;
import com.studyhub.sth.libs.core.BaseWSResponseMessage;
import com.studyhub.sth.libs.core.WSMessageContext;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ReuniaoEncontradaMensagem extends BaseWSResponseMessage<ReunioesMensagens> {
    private UUID reuniaoId;
    private StatusReuniao status;

    public ReuniaoEncontradaMensagem(UUID reuniaoId, StatusReuniao status) {
        super(WSMessageContext.REUNIOES, ReunioesMensagens.REUNIAO_ENCONTRADA);
        this.reuniaoId = reuniaoId;
        this.status = status;
    }
}
