package com.studyhub.sth.application.dtos.artigo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtigoUpdateDto {
    private String titulo;
    private String conteudo;
}
