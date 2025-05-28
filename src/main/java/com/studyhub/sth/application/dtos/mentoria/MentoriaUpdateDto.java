package com.studyhub.sth.application.dtos.mentoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoriaUpdateDto {
    private DayOfWeek diaDaSemana;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
