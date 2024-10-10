package com.studyhub.sth.dtos.mentor;

import com.studyhub.sth.dtos.users.UsuarioDto;
import com.studyhub.sth.entities.Mentor;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentorDTO{
    UUID id;
    UsuarioDto usuarioDto;
    //List<SquadDto> squadDtos;
}
