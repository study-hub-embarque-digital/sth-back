package com.studyhub.sth.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "conteudos_estudo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "conteudoEstudoId")
public class ConteudoEstudo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID conteudoEstudoId;

    @Column(length = 350)
    private String url;

    @Column(length = 350)
    private String imagem;

    @Column(length = 350)
    private String tipo;

    @Column(length = 350)
    private String descricao;

    @Column(length = 350)
    private String titulo;

    @ManyToOne()
    @JoinColumn(name = "room_id")
    private Room room;

    public ConteudoEstudo(String url, String imagem, String tipo, String descricao, String titulo, Room room) {
        this.url = url;
        this.imagem = imagem;
        this.tipo = tipo;
        this.descricao = descricao;
        this.titulo = titulo;
        this.room = room;
    }
}
