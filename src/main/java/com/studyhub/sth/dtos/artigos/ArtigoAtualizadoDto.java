package com.studyhub.sth.dtos.artigos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtigoAtualizadoDto {
    private String titulo;
    private String conteudo;
    private String autor;
    private List<String> tags;
}
