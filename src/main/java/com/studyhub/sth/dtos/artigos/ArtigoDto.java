package com.studyhub.sth.dtos.artigos;

import com.studyhub.sth.dtos.tags.TagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtigoDto {
    private UUID artigoId;
    private String titulo;
    private String conteudo;
    private String autor;
    private List<TagDto> tags;
}
