package com.studyhub.sth.application.events;

import com.studyhub.sth.domain.entities.Usuario;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class EntrarReuniaoEvento {
    private UUID salaTematicaId;
    private UUID usuarioAtual;
}
