package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.mentoria.MentoriaCreateDto;
import com.studyhub.sth.application.dtos.mentoria.MentoriaCreateWithEncontrosDto;
import com.studyhub.sth.application.dtos.mentoria.MentoriaDto;
import com.studyhub.sth.application.dtos.mentoria.MentoriaUpdateDto;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.domain.entities.Mentoria;
import com.studyhub.sth.domain.entities.Squad;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface IMentoriaService {
    public MentoriaDto save(MentoriaCreateDto dto);

    public MentoriaDto findById(UUID id);

    public List<MentoriaDto> findAll();

    public MentoriaDto update(UUID id, MentoriaUpdateDto dto);

    public void deleteById(UUID id);

    public MentoriaDto saveWithEncontros(MentoriaCreateWithEncontrosDto dto);
}
