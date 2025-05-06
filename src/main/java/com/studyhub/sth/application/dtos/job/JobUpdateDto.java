package com.studyhub.sth.application.dtos.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobUpdateDto {
    private String cargo;
    private String areaAtuacao;
    private LocalDate dataTermino;
}
