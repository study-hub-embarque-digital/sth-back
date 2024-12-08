package com.studyhub.sth.domain.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "instituicao_ensino")
@Table(name = "instituicao_ensino")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "instituicaoEnsinoId")
public class InstituicaoEnsino {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID instituicaoEnsinoId;

    private String nome;
    private String endereco;
    private String coordenador;

    @OneToMany(mappedBy = "instituicaoEnsino", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Aluno> alunos = new ArrayList<>();
    
    //@OneToMany(mappedBy = "instituicaoEnsino")
    //private List<Squad> squads;

}
