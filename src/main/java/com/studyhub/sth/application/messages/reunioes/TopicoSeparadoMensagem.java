package com.studyhub.sth.application.messages.reunioes;

import com.studyhub.sth.libs.core.BaseWSResponseMessage;
import com.studyhub.sth.libs.core.WSMessageContext;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TopicoSeparadoMensagem extends BaseWSResponseMessage<ReunioesMensagens> {
    private List<String> topicos;

    public TopicoSeparadoMensagem(List<String> topicos) {
        super(WSMessageContext.REUNIOES_TOPICOS, ReunioesMensagens.TOPICOS_SEPARADOS);
        this.topicos = topicos;
    }
}
