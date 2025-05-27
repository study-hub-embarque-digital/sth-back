package com.studyhub.sth.domain.entities;

import com.studyhub.sth.domain.enums.Ciclo;
import com.studyhub.sth.domain.enums.TipoSquad;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "squads")
@Table(name = "squads")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "squadId")
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID squadId;

    @Column(nullable = false, length = 255)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoSquad tipo;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "instituicao_ensino_id")
    private InstituicaoEnsino instituicaoEnsino;

    @ManyToMany
    @JoinTable(name = "squads_representantes",
            joinColumns = @JoinColumn(name = "squad_id"),
            inverseJoinColumns = @JoinColumn(name = "representante_id"))
    private List<Representante> representantes;

    @OneToMany(mappedBy = "squad")
    private List<Aluno> alunos;

    @Pattern(regexp = "\\d{4}\\.[12]", message = "Formato inválido. Use o padrão AAAA.S, exemplo: 2025.1")
    @Column(nullable = false, length = 10)
    private String semestre;

    @Column(nullable = false)
    private Boolean selecionadoParaDemoDay;

    @Enumerated(EnumType.STRING)
    private Ciclo ciclo;

    public Squad(String nome, TipoSquad tipo, String semestre, Ciclo ciclo) {
        this.nome = nome;
        this.tipo = tipo;
        this.semestre = semestre;
        this.ciclo = ciclo;
        this.selecionadoParaDemoDay = false;
    }
}
