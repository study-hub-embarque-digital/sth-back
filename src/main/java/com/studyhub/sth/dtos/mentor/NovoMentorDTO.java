package com.studyhub.sth.dtos.mentor;

import com.studyhub.sth.dtos.users.NovoUsuarioDto;
import jakarta.validation.Valid;

public record NovoMentorDTO(
        @Valid NovoUsuarioDto usuarioDto
) {
}
