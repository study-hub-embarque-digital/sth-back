package com.studyhub.sth.dtos.mentor;

import com.studyhub.sth.dtos.users.NovoUsuarioDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NovoMentorDTO {
    @Valid NovoUsuarioDto usuarioDto;
}
