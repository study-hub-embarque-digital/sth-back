package com.studyhub.sth.domain.entities;

import com.studyhub.sth.domain.enums.Dificuldade;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "topicoId")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID topicoId;

    @Column
    private String titulo;

    @Column
    @Enumerated(EnumType.STRING)
    private Dificuldade dificuldade;

    @ManyToOne()
    @JoinColumn(name = "topico_pai_id")
    private Topico topicoPai;

    @OneToMany(mappedBy = "topicoPai")
    private List<Topico> subtopicos;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room rooms;

    public Topico(Room room, Dificuldade dificuldade, String titulo) {
        this.dificuldade = dificuldade;
        this.titulo = titulo;
        this.subtopicos = new ArrayList<>();
        this.rooms = room;
    }

    public Topico(Room room, Topico topicoPai, String titulo) {
        this.titulo = titulo;
        this.topicoPai = topicoPai;
        this.rooms = room;
    }

    public void addSubtopicos(List<Topico> subtopicos) {
        this.subtopicos.addAll(subtopicos);
    }
}
