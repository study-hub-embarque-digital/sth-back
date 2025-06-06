package com.studyhub.sth.application.dtos.conteudoEstudo;


import com.studyhub.sth.domain.entities.ConteudoEstudo;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ConteudoEstudoDto {
    private UUID conteudoEstudoId;
    private String url;
    private String imagem;
    private String tipo;
    private String descricao;
    private String titulo;

    public ConteudoEstudoDto(ConteudoEstudo c) {
        this.conteudoEstudoId = c.getConteudoEstudoId();
        this.url = c.getUrl();
        this.imagem = c.getImagem();
        this.tipo = c.getTipo();
        this.descricao = c.getDescricao();
        this.titulo = c.getTitulo();
    }
}
