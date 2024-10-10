package com.studyhub.sth.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
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

    // obs: entidades que serão criadas futuramente para PI
    //private Ciclo ciclo;
    //private Formulario formulario;
}
