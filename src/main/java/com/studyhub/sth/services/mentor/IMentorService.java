package com.studyhub.sth.services.mentor;

import com.studyhub.sth.dtos.mentor.MentorUpdateDto;
import com.studyhub.sth.dtos.mentor.MentorDto;
import com.studyhub.sth.dtos.mentor.MentorCreateDto;
import com.studyhub.sth.dtos.squad.SquadDTO;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;

import java.util.List;
import java.util.UUID;

public interface IMentorService {
    MentorDto criar(MentorCreateDto dto);
    List<MentorDto> listar();
    MentorDto buscarPorId(UUID id) throws ElementoNaoEncontradoExcecao;
    void deletarPorId(UUID id) throws ElementoNaoEncontradoExcecao;
    MentorDto atualizar(UUID id , MentorUpdateDto dto) throws ElementoNaoEncontradoExcecao;
    List<SquadDTO> listarSquads(UUID id);
    MentorDto buscarPorNome(String nome) throws ElementoNaoEncontradoExcecao;
}
