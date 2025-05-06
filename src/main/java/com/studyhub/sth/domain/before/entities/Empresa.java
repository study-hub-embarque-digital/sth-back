package com.studyhub.sth.domain.before.entities;

import com.studyhub.sth.domain.squads.Squad;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "empresas")
@Table(name = "empresas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "empresaId")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID empresaId;

    @Column(length = 350, nullable = false)
    private String razaoSocial;

    @Column(length = 350, nullable = false)
    private String nomeFantasia;

    @Column(length = 14)
    private String telefone;

    @Column(length = 350, unique = true)
    private String email;

    @Column(length = 14, unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "empresa")
    private List<Representante> representantes;

    private boolean isActive;

    private boolean isInPortoDigital;

    private String site;

    //periodo de interesse


    @OneToMany(mappedBy = "empresa")
    private List<Squad> squads;

    //lista aluno

    //lista rpresentantes

}