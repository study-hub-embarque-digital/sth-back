package com.studyhub.sth.domain.entities;

import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
import com.studyhub.sth.domain.enums.Ciclo;
import com.studyhub.sth.domain.enums.Cursos;
import com.studyhub.sth.domain.enums.Periodo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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

    @Enumerated(EnumType.STRING)
    private Cursos curso;

    @Pattern(regexp = "\\d{4}\\.[12]", message = "Formato inválido. Use o padrão AAAA.S, exemplo: 2025.1")
    @Column(nullable = false, length = 10)
    private String entrada;

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
    private Boolean isWorkingInIt;

    @Column(name = "is_exempted_residence",nullable = true)
    private Boolean isExemptedResidence;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Job> jobs = new ArrayList<>();

    //lista squads

    public void atualizar(AlunoUpdateDto alunoAtualizadoDto) {
        this.periodo = alunoAtualizadoDto.getPeriodo();
        this.curso = alunoAtualizadoDto.getCurso();
        this.ciclo = alunoAtualizadoDto.getCiclo();
        this.isExemptedResidence = alunoAtualizadoDto.getIsExemptedResidence();
        this.isWorkingInIt = alunoAtualizadoDto.getIsWorkingInIt();
    }
}
