package com.studyhub.sth.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.studyhub.sth.application.dtos.job.JobCreateDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;


@Entity(name = "job")
@Table(name = "jobs")
@Getter
@Setter
@EqualsAndHashCode(of = "jobId")
@AllArgsConstructor
@NoArgsConstructor
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

    private Date dataInicio;
    private Date dataTermino;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Job(JobCreateDto dto) {
        this.cargo = dto.getCargo();
        this.areaAtuacao = dto.getAreaAtuacao();
        this.dataInicio = dto.getDataInicio();
    }
}
