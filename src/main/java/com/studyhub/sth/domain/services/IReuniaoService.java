package com.studyhub.sth.domain.services;

import com.studyhub.sth.domain.annotations.CurrentUser;
import com.studyhub.sth.domain.entities.Reuniao;
import com.studyhub.sth.domain.entities.Usuario;

import java.util.UUID;

public interface IReuniaoService {
    UUID entrarReuniaoSalaTematica(UUID salaTematicaId, Usuario usuarioAtual);
}
