package com.studyhub.sth.domain.entities;

import com.studyhub.sth.libs.core.EntidadeBase;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "conteudos_estudo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConteudoEstudo extends EntidadeBase {

//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID conteudoEstudoId;

    private String link;

    @ManyToOne()
    @JoinColumn(name = "room_id")
    private Room room;
}
