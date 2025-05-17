package com.studyhub.sth.application.dtos.artigo;

import com.studyhub.sth.application.dtos.tag.TagDto;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtigoDto {
    private UUID id;
    private String titulo;
    private String conteudo;
    private UsuarioDto autor;
    private List<TagDto> tags;
    private Date atualizadoEm;
}
