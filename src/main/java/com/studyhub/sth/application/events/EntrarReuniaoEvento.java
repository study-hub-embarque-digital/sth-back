package com.studyhub.sth.application.events;

import lombok.*;

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
