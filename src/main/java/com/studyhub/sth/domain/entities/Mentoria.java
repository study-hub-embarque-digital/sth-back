package com.studyhub.sth.domain.entities;

import com.studyhub.sth.application.dtos.mentoria.MentoriaCreateDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mentorias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Mentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "squad_id")
    private Squad squad;

    @Enumerated(EnumType.STRING)
    private DayOfWeek diaDaSemana;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    @OneToMany(mappedBy = "mentoria", cascade = CascadeType.ALL)
    private List<Encontro> encontros;

    public Mentoria(Squad squad, DayOfWeek diaDaSemana, LocalDate dataInicio, LocalDate dataFim) {
        this.squad = squad;
        this.diaDaSemana = diaDaSemana;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}