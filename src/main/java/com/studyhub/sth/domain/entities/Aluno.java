package com.studyhub.sth.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
import com.studyhub.sth.domain.enums.Ciclo;
import com.studyhub.sth.domain.enums.Periodo;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "alunos")
@Table(name = "alunos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "alunoId")
public class Aluno {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID alunoId;

    @OneToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String curso;

    @ManyToOne()
    @JoinColumn(name = "instituicao_ensino_id")
    private InstituicaoEnsino instituicaoEnsino;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Ciclo ciclo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Periodo periodo;

    @ManyToOne()
    @JoinColumn(name = "squad_id")
    private Squad squad;

    @Column(name = "is_working_in_it", nullable = true)
    private boolean isWorkingInIt;

    @Column(name = "is_exempted_residence",nullable = true)
    private boolean isExemptedResidence;

    //lista squads

    public void atualizar(AlunoUpdateDto alunoAtualizadoDto) {
        this.periodo = alunoAtualizadoDto.getPeriodo();
        this.curso = alunoAtualizadoDto.getCurso();
        this.ciclo = alunoAtualizadoDto.getCiclo();
        this.isExemptedResidence = alunoAtualizadoDto.isExemptedResidence();
        this.isWorkingInIt = alunoAtualizadoDto.isWorkingInIt();
    }
}
