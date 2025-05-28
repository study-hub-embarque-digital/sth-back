package com.studyhub.sth.application.dtos.encontro;

import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.mentoria.MentoriaDto;
import com.studyhub.sth.domain.enums.PlataformaReuniao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EncontroDto {
    private UUID id;
    private Date data;
    private String resumo;
    private String linkReuniao;
    private PlataformaReuniao plataforma;
    private List<AlunoDto> alunosPresentes;
    private LocalTime horario;
}
