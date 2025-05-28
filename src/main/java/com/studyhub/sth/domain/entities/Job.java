package com.studyhub.sth.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.studyhub.sth.application.dtos.job.JobCreateDto;
import com.studyhub.sth.domain.enums.TipoVinculo;
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
    @JoinColumn(name = "aluno_id")         // antes era usuario_id
    private Aluno aluno;

    @Enumerated(EnumType.STRING)
    private TipoVinculo tipoVinculo;

    private String atividadesDesenvolvidas;

    public Job(JobCreateDto dto) {
        this.cargo = dto.getCargo();
        this.areaAtuacao = dto.getAreaAtuacao();
        this.dataInicio = dto.getDataInicio();
        this.tipoVinculo = dto.getTipoVinculo();
        this.atividadesDesenvolvidas = dto.getAtividadesDesenvolvidas();;
    }
}
