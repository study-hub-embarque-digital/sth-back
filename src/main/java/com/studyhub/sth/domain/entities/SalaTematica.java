package com.studyhub.sth.domain.entities;

import com.studyhub.sth.libs.core.EntidadeBase;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "salaTematica")
@Table(name = "salas_tematicas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SalaTematica extends EntidadeBase {
    @ManyToOne()
    @JoinColumn(name = "room_id")
    private Room room;

    @Column
    private int duracao;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
}
