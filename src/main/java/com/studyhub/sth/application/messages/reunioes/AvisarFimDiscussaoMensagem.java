package com.studyhub.sth.application.messages.reunioes;

import com.studyhub.sth.domain.enums.StatusReuniao;
import com.studyhub.sth.libs.core.BaseWSResponseMessage;
import com.studyhub.sth.libs.core.WSMessageContext;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AvisarFimDiscussaoMensagem extends BaseWSResponseMessage<ReunioesMensagens> {
    private UUID reuniaoId;
    private StatusReuniao status;

    public AvisarFimDiscussaoMensagem(UUID reuniaoId, StatusReuniao status) {
        super(WSMessageContext.REUNIOES, ReunioesMensagens.FINALIZAR_DISCUSSAO);
        this.reuniaoId = reuniaoId;
        this.status = status;
    }
}
