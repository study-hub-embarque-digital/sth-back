package com.studyhub.sth.application.dtos.mentor;

import com.studyhub.sth.application.dtos.users.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentorListDto {
    UUID id;
    UsuarioDto usuarioDto;
}
