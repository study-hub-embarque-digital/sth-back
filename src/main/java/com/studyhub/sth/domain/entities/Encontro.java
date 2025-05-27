package com.studyhub.sth.domain.entities;

import com.studyhub.sth.domain.enums.PlataformaReuniao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "encontros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Encontro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "mentoria_id")
    private Mentoria mentoria;

    private LocalDate data; // data específica do encontro
    private LocalTime horario;


    @Column(columnDefinition = "TEXT")
    private String resumo;

    @Column(length = 500)
    private String linkReuniao;

    @Enumerated(EnumType.STRING)
    private PlataformaReuniao plataforma;

    @ManyToMany
    @JoinTable(
            name = "encontros_alunos_presentes",
            joinColumns = @JoinColumn(name = "encontro_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunosPresentes;
}