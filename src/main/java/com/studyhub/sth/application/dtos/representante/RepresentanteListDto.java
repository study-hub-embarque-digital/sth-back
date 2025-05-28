package com.studyhub.sth.application.dtos.representante;

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
public class RepresentanteListDto {
    private UUID id;
    private UsuarioDto usuarioDto;
}
