package com.studyhub.sth.application.dtos.squad;

import com.studyhub.sth.domain.enums.Ciclo;
import com.studyhub.sth.domain.enums.TipoSquad;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
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
    private UUID instituicaoDeEnsinoId;
    private List<UUID> representantesIds;
    private List<UUID> alunosIds;
    @Valid
    private String semestre;
    private Ciclo ciclo;
}
