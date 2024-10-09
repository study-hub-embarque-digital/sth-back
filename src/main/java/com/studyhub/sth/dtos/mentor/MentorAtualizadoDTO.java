package com.studyhub.sth.dtos.mentor;

import com.studyhub.sth.dtos.users.UsuarioDto;
import jakarta.validation.Valid;

public record MentorAtualizadoDTO(
       @Valid UsuarioDto usuarioDto
) {
}
