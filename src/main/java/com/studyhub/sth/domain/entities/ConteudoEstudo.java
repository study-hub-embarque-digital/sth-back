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
    private String link;

    @Column(length = 350)
    private String linkEncorporacao;

    @ManyToOne()
    @JoinColumn(name = "room_id")
    private Room room;
}
