package com.studyhub.sth.application.messages.reunioes;

import com.studyhub.sth.libs.core.BaseWSResponseMessage;
import com.studyhub.sth.libs.core.WSMessageContext;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AvisarUsuarioSuaVezApresentarMensagem extends BaseWSResponseMessage<ReunioesMensagens> {
    public AvisarUsuarioSuaVezApresentarMensagem() {
        super(WSMessageContext.REUNIOES_APRESENTACOES, ReunioesMensagens.SUA_VEZ_APRESENTAR);
    }
}
