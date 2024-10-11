package com.studyhub.sth.entities;

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

    // Link do conteúdo (pode ser um vídeo, artigo, etc.)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

}
