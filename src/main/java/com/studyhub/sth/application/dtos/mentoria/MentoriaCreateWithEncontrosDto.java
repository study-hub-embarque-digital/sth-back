package com.studyhub.sth.application.dtos.mentoria;

import com.studyhub.sth.application.dtos.encontro.EncontroCreateListDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentoriaCreateWithEncontrosDto {
    private UUID squadId;
    private DayOfWeek diaDaSemana;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<EncontroCreateListDto> encontros;
}