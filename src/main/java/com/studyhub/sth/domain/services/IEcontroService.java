package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.encontro.EncontroCreateAndUpdateDto;
import com.studyhub.sth.application.dtos.encontro.EncontroDto;
import com.studyhub.sth.domain.entities.Mentoria;

import java.util.List;
import java.util.UUID;

public interface IEcontroService {
    public EncontroDto save(EncontroCreateAndUpdateDto dto, UUID mentoriaId);
    public List<EncontroDto> saveList(Mentoria mentoria);
    public EncontroDto update(UUID id, EncontroCreateAndUpdateDto dto);
    public void deleteById(UUID id);
    List<EncontroDto> listarPorMentoriaId(UUID mentoriaId);
}
