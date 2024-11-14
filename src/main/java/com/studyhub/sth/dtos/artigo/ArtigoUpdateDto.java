package com.studyhub.sth.dtos.artigo;

import com.studyhub.sth.dtos.tag.TagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtigoUpdateDto {
    private String titulo;
    private String conteudo;
}
