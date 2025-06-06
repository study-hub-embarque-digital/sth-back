package com.studyhub.sth.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GeneratedColumn;

import java.util.UUID;

@Entity
@Table(name = "salas_tematicas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "salaTematicaId")
public class SalaTematica {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID salaTematicaId;

    @ManyToOne()
    @JoinColumn(name = "room_id")
    private Room room;

    @Column
    private int duracao;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    public SalaTematica(Room room, Topico topico) {
        this.room = room;
        this.duracao = 0;
        this.topico = topico;
    }
}
