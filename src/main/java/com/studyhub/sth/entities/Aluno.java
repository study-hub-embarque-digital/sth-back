package com.studyhub.sth.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.studyhub.sth.dtos.alunos.AlunoUpdateDto;
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

    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private int periodo;
    private String curso;

    @ManyToOne()
    @JoinColumn(name = "instituicao_ensino_id")
    @JsonBackReference
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
