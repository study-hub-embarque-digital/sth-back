package com.studyhub.sth.application.dtos.rooms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.studyhub.sth.application.dtos.conteudoEstudo.GeneratedConteudoEstudoDto;
import com.studyhub.sth.application.dtos.topicos.GeneratedTopicoDto;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
public class GeneratedRoomDto {
    private String nome;
    private String descricao;
    private String stack;
    private String cor;
    @JsonProperty("cor_fonte")
    private String corFonte;
    private List<GeneratedConteudoEstudoDto> biblioteca;
    private List<GeneratedTopicoDto> topicos;
}
