package com.studyhub.sth.entities;

import com.studyhub.sth.dtos.alunos.AlunoAtualizadoDto;
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
    private InstituicaoEnsino instituicaoEnsino;
//    private Ciclo ciclo;
//    private Squad squad;

    public void atualizar(AlunoAtualizadoDto alunoAtualizadoDto) {
        this.periodo = alunoAtualizadoDto.getPeriodo();
        this.curso = alunoAtualizadoDto.getCurso();
    }
}
