package com.studyhub.sth.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GeneratedColumn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "reuniao")
@Table(name = "reunioes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "reuniaoId")
public class Reuniao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID reuniaoId;

    @ManyToOne
    @JoinColumn(nullable = true, name = "sala_tematica_id")
    private SalaTematica salaTematica;

    @ManyToMany()
    @JoinTable(name = "reuniao_participantes")
    private List<Usuario> participantes;

    private Date criadoEm;

    private UUID criadoPor;

    public Reuniao(SalaTematica salaTematica, List<Usuario> participantes) {
        this.salaTematica = salaTematica;

        if (this.participantes == null) {
            this.participantes = new ArrayList<>();
        }

        this.participantes.addAll(participantes);
    }

    public Reuniao(SalaTematica salaTematica, Usuario participanteInicial) {
        this.salaTematica = salaTematica;

        if (this.participantes == null) {
            this.participantes = new ArrayList<>();
        }

        this.participantes.add(participanteInicial);
    }

    public void addParticipante(Usuario participante) {
        boolean hasParticipant = this.participantes.stream().anyMatch(usuario -> usuario.equals(participante));

        if (hasParticipant) {
            return;
        }

        this.participantes.add(participante);
    }
}
