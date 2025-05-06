package com.studyhub.sth.application.messages.reunioes;

import com.studyhub.sth.domain.before.enums.StatusReuniao;
import com.studyhub.sth.libs.core.BaseWSResponseMessage;
import com.studyhub.sth.libs.core.WSMessageContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
public class AvisarInicioDiscussaoMensagem extends BaseWSResponseMessage<ReunioesMensagens> {
    private UUID reuniaoId;
    private StatusReuniao status;
    private List<String> topicos;

    public AvisarInicioDiscussaoMensagem(UUID reuniaoId, StatusReuniao status, List<String> topicos) {
        super(WSMessageContext.REUNIOES, ReunioesMensagens.INICIAR_DISCUSSAO);
        this.reuniaoId = reuniaoId;
        this.status = status;
        this.topicos = topicos;
    }
}
