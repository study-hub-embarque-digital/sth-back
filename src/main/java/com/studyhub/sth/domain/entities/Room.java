package com.studyhub.sth.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "rooms")
@Table(name = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "roomId")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roomId;

    @OneToMany(mappedBy = "room")
    private List<ConteudoEstudo> conteudosRecomendados;

    //private UUID criador;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 450)
    private String title;

    @Column
    private String image;

    @OneToMany()
    private List<Topico> topicos;

    public Room(String description, String title, String image) {
        this.description = description;
        this.title = title;
        this.image = image;
    }

    public void addTopicos(List<Topico> topicos) {
        this.topicos.addAll(topicos);
    }
}