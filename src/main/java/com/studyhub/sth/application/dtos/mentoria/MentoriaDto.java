package com.studyhub.sth.application.dtos.mentoria;

import com.studyhub.sth.application.dtos.encontro.EncontroDto;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoriaDto {
    private UUID id;
    private SquadDTO squadDTO;
    private DayOfWeek diaDaSemana;
    private Date dataInicio;
    private Date dataFim;
    private List<EncontroDto> encontros;
}
