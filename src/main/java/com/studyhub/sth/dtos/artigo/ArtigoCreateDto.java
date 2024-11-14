package com.studyhub.sth.dtos.artigo;

import com.studyhub.sth.dtos.tag.TagDto;
import com.studyhub.sth.dtos.users.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtigoCreateDto {
    private String titulo;
    private String conteudo;
    private UsuarioDto autor;
    private List<TagDto> tags;
}
