package com.studyhub.sth.dtos.mentor;

import com.studyhub.sth.dtos.squad.SquadDTO;
import com.studyhub.sth.dtos.users.UsuarioDto;
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
public class MentorDto {
    UUID id;
    UsuarioDto usuarioDto;
    List<SquadDTO> squadDtos;
}
