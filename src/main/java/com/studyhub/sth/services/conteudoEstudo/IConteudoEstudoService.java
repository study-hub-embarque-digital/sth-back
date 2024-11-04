package com.studyhub.sth.services.conteudoEstudo;

import com.studyhub.sth.dtos.conteudoEstudo.ConteudoEstudoCreateDto;
import com.studyhub.sth.dtos.conteudoEstudo.ConteudoEstudoDto;
import com.studyhub.sth.dtos.conteudoEstudo.ConteudoEstudoUpdateDto;

import java.util.List;
import java.util.UUID;

public interface IConteudoEstudoService {

    ConteudoEstudoCreateDto criarConteudoEstudo(ConteudoEstudoCreateDto dto);

    List<ConteudoEstudoCreateDto> listarConteudosEstudo();

    ConteudoEstudoDto obterConteudoEstudoPorId(UUID id);

    ConteudoEstudoDto atualizarConteudoEstudo(UUID id, ConteudoEstudoUpdateDto dto);

    void deletarConteudoEstudo(UUID id);
}
