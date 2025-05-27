package com.studyhub.sth.application.dtos.encontro;

import com.studyhub.sth.domain.enums.PlataformaReuniao;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EncontroCreateListDto {
    private LocalTime horario;
    private String linkReuniao;
    @Valid
    private PlataformaReuniao plataforma;
}
