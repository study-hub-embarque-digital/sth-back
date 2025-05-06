package com.studyhub.sth.application.dtos.squad;

import com.studyhub.sth.domain.squads.TipoSquad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SquadCreateDTO {
    private String nome;
    private TipoSquad tipo;
    private UUID mentorId;
    private UUID empresaId;
    private List<UUID> representantesIds;
    private List<UUID> alunosIds;
}
