package com.studyhub.sth.application.dtos.admins;

import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AdminCreateDto {
    private UsuarioCreateDto novoUsuarioDto;
}
