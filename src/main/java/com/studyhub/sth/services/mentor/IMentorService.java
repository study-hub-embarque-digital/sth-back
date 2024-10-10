package com.studyhub.sth.services.mentor;

import com.studyhub.sth.dtos.mentor.MentorAtualizadoDTO;
import com.studyhub.sth.dtos.mentor.MentorDTO;
import com.studyhub.sth.dtos.mentor.NovoMentorDTO;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;

import java.util.List;
import java.util.UUID;

public interface IMentorService {
    MentorDTO criar(NovoMentorDTO dto);
    List<MentorDTO> listar();
    MentorDTO buscarPorId(UUID id) throws ElementoNaoEncontradoExcecao;
    void deletarPorId(UUID id) throws ElementoNaoEncontradoExcecao;
    MentorDTO atualizar(UUID id , MentorAtualizadoDTO dto) throws ElementoNaoEncontradoExcecao;
    //List<SquadDto> listarSquads(UUID id);
    MentorDTO buscarPorNome(String nome) throws ElementoNaoEncontradoExcecao;
}
