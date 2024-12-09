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

    private UUID criador;

    @OneToMany(mappedBy = "room")
    private List<ConteudoEstudo> conteudosEstudo;

    // obs: entidades que serão criadas futuramente para PI
    // private Ciclo ciclo;
    // private Formulario formulario;
}
