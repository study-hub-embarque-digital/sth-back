package com.studyhub.sth.dtos.artigo;

import com.studyhub.sth.dtos.users.UsuarioDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<Tag> tags;
}
