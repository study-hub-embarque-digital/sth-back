package com.studyhub.sth.domain.entities;

import com.studyhub.sth.libs.core.EntidadeBase;
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

    private String link;

    @ManyToOne()
    @JoinColumn(name = "room_id")
    private Room room;
}
