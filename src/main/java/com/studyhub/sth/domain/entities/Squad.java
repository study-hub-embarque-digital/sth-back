package com.studyhub.sth.domain.entities;

import com.studyhub.sth.domain.enums.TipoSquad;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
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

    @ManyToMany
    @JoinTable(name = "squad_representante",
            joinColumns = @JoinColumn(name = "squad_id"),
            inverseJoinColumns = @JoinColumn(name = "representante_id"))
    private List<Representante> representantes;


    @OneToMany(mappedBy = "squad")
    private List<Aluno> alunos;


}
