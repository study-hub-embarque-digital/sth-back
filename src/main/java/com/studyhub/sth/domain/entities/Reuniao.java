package com.studyhub.sth.domain.entities;

import com.studyhub.sth.libs.core.EntidadeBase;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "reuniao")
@Table(name = "reunioes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Reuniao extends EntidadeBase {
    @ManyToOne
    @JoinColumn(name = "sala_tematica_id")
    private SalaTematica salaTematica;

    @ManyToMany()
    @JoinTable(name = "reuniao_participantes")
    private List<Usuario> participantes;
}
