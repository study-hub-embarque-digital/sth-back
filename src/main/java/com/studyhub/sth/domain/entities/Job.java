package com.studyhub.sth.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Entity(name = "job")
@Table(name = "jobs")
@Getter
@Setter
@EqualsAndHashCode(of = "jobId")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID jobId;

    @ManyToOne()
    @JoinColumn(name = "empregador_id")
    @JsonManagedReference
    private Empregador empregador;
    private String cargo;
    private String areaAtuacao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
