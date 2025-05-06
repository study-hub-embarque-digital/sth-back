package com.studyhub.sth.application.messages.reunioes;

import com.studyhub.sth.domain.before.enums.StatusReuniao;
import com.studyhub.sth.libs.core.BaseWSResponseMessage;
import com.studyhub.sth.libs.core.WSMessageContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
public class IniciaApresentacaoMensagem extends BaseWSResponseMessage<ReunioesMensagens> {
    private UUID reuniaoId;
    private StatusReuniao status;
    private int apresentacaoAtual;

    public IniciaApresentacaoMensagem(UUID reuniaoId, StatusReuniao status, int apresentacaoAtual) {
        super(WSMessageContext.REUNIOES, ReunioesMensagens.NOVO_USUARIO_APRESENTAR);
        this.reuniaoId = reuniaoId;
        this.status = status;
        this.apresentacaoAtual = apresentacaoAtual;
    }
}
