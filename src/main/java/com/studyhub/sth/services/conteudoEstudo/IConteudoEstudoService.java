package com.studyhub.sth.services.conteudoEstudo;

import com.studyhub.sth.dtos.conteudoEstudo.ConteudoEstudoDto;
import com.studyhub.sth.entities.ConteudoEstudo;

import java.util.List;
import java.util.UUID;

public interface IConteudoEstudoService {

    ConteudoEstudo criarConteudoEstudo(ConteudoEstudoDto dto);

    List<ConteudoEstudo> listarConteudosEstudo();

    ConteudoEstudo obterConteudoEstudoPorId(UUID id);

    ConteudoEstudo atualizarConteudoEstudo(UUID id, ConteudoEstudoDto dto);

    void deletarConteudoEstudo(UUID id);
}
