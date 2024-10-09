package com.studyhub.sth.dtos.mentor;

import com.studyhub.sth.dtos.users.UsuarioDto;
import com.studyhub.sth.entities.Mentor;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public record MentorDTO(
    UUID id,
    UsuarioDto usuarioDto
    //, List<SquadDto> squadDtos
) {}
