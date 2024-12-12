package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.mentor.MentorUpdateDto;
import com.studyhub.sth.application.dtos.mentor.MentorDto;
import com.studyhub.sth.application.dtos.mentor.MentorCreateDto;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;

import java.util.List;
import java.util.UUID;

public interface IMentorService {
    String criar(MentorCreateDto dto) throws Exception;
    List<MentorDto> listar();
    MentorDto buscarPorId(UUID id) throws ElementoNaoEncontradoExcecao;
    void deletarPorId(UUID id) throws ElementoNaoEncontradoExcecao;
    MentorDto atualizar(UUID id , MentorUpdateDto dto) throws ElementoNaoEncontradoExcecao;
    List<SquadDTO> listarSquads(UUID id);
    MentorDto buscarPorNome(String nome) throws ElementoNaoEncontradoExcecao;
}
