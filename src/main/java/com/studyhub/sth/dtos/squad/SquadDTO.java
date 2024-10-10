package com.studyhub.sth.dtos.squad;

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
    private String tipo;
    //private UUID mentorId;
    private UUID empresaId;
}
