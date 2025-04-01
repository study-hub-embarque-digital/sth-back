package com.studyhub.sth.domain.entities;

import com.studyhub.sth.domain.enums.Dificuldade;
import jakarta.persistence.*;
import lombok.*;

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
    private Dificuldade dificuldade;

    @ManyToOne()
    @JoinColumn(name = "topico_pai_id")
    private Topico topicoPai;

    @OneToMany(mappedBy = "topicoPai")
    private List<Topico> subtopicos;

    @ManyToMany
    @JoinTable(name = "rooms_topicos")
    private List<Room> rooms;
}
