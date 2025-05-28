package com.studyhub.sth.application.dtos.mentoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoriaCreateDto {
    private UUID squadId;
    private DayOfWeek diaDaSemana;
    private Date dataInicio;
    private Date dataFim;
}
