package com.studyhub.sth.application.messages.reunioes;

import com.studyhub.sth.application.dtos.apresentacoesReunioes.ListApresentacaoReuniaoDto;
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
public class IniciaApresentacoesMensagem extends BaseWSResponseMessage<ReunioesMensagens> {
    private UUID reuniaoId;
    private StatusReuniao status;
    private int apresentacaoAtual;
    private List<ListApresentacaoReuniaoDto> apresentacoes;

    public IniciaApresentacoesMensagem(UUID reuniaoId, StatusReuniao status, int apresentacaoAtual, List<ListApresentacaoReuniaoDto> apresentacoes) {
        super(WSMessageContext.REUNIOES, ReunioesMensagens.APRESENTACOES_INICIADAS);
        this.reuniaoId = reuniaoId;
        this.status = status;
        this.apresentacoes = apresentacoes;
        this.apresentacaoAtual = apresentacaoAtual;
    }
}
