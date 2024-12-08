package com.studyhub.sth.application.dtos.mentor;

import com.studyhub.sth.application.dtos.users.UsuarioUpdateDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentorUpdateDto {
    @Valid
    UsuarioUpdateDto usuarioDto;
}
