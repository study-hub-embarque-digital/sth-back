package com.studyhub.sth.application.dtos.squad;

import com.studyhub.sth.domain.enums.TipoSquad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SquadDTO {
    private UUID id;
    private String nome;
    private TipoSquad tipo;
    private UUID mentorId;
    private UUID empresaId;
}
