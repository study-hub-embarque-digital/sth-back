package com.studyhub.sth.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
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

    private int periodo;
    private String curso;

    @ManyToOne()
    @JoinColumn(name = "instituicao_ensino_id")
    private InstituicaoEnsino instituicaoEnsino;
//    private Ciclo ciclo;

    @ManyToOne()
    @JoinColumn(name = "squad_id")
    private Squad squad;

    public void atualizar(AlunoUpdateDto alunoAtualizadoDto) {
        this.periodo = alunoAtualizadoDto.getPeriodo();
        this.curso = alunoAtualizadoDto.getCurso();
    }
}
