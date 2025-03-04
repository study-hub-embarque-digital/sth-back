package com.studyhub.sth.domain.entities;

import com.studyhub.sth.domain.enums.Dificuldade;
import com.studyhub.sth.libs.core.EntidadeBase;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "topicos")
@Table(name = "topicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Topico extends EntidadeBase {
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
