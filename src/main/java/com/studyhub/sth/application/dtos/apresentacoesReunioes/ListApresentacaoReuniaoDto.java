package com.studyhub.sth.application.dtos.apresentacoesReunioes;

import com.studyhub.sth.domain.entities.ApresentacaoReuniao;
import com.studyhub.sth.domain.entities.Topico;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
public class ListApresentacaoReuniaoDto {
    private UUID apresentacaoReuniaoId;
    private String nome;
    private int ordem;
    private List<String> topicos;

    public ListApresentacaoReuniaoDto(UUID apresentacaoReuniaoId, String nome, int ordem, List<String> topicos) {
        this.apresentacaoReuniaoId = apresentacaoReuniaoId;
        this.nome = nome;
        this.ordem = ordem;
        this.topicos = topicos;
    }

    public ListApresentacaoReuniaoDto(ApresentacaoReuniao apresentacaoReuniao) {
        this.apresentacaoReuniaoId = apresentacaoReuniao.getApresentacaoReuniaoId();
        this.nome = apresentacaoReuniao.getUsuario().getNome();
        this.ordem = apresentacaoReuniao.getOrdem();
        this.topicos = apresentacaoReuniao.getTopicos().stream().map(Topico::getTitulo).toList();
    }
}
