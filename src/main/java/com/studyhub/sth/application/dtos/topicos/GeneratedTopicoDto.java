package com.studyhub.sth.application.dtos.topicos;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
public class GeneratedTopicoDto {
    private String titulo;
    private String dificuldade;
    private List<String> subtopicos;
}
