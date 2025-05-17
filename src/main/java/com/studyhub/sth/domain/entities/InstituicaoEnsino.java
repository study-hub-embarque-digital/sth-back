package com.studyhub.sth.domain.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.ColumnDefault;

@Entity(name = "instituicoes_ensino")
@Table(name = "instituicoes_ensino")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "instituicaoEnsinoId")
public class InstituicaoEnsino {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID instituicaoEnsinoId;
    private String coordenador;
    @Column(length = 350, nullable = false)
    @ColumnDefault("''")
    private String razaoSocial;

    @Column(length = 350, nullable = false)
    @ColumnDefault("''")
    private String nomeFantasia;

    @Column(length = 14)
    private String telefone;

    @Column(length = 350, unique = true)
    private String email;

    @Column(length = 14, unique = true)
    private String cnpj;

    private Boolean isActive;

    private String site;

    @OneToOne()
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "instituicaoEnsino")
    private List<Aluno> alunos = new ArrayList<>();
    
    @OneToMany(mappedBy = "instituicaoEnsino")
    private List<Squad> squads;

    public InstituicaoEnsino(String coordenador, String razaoSocial, String nomeFantasia, String telefone, String email, String cnpj, String site, Endereco endereco) {
        this.coordenador = coordenador;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.telefone = telefone;
        this.email = email;
        this.cnpj = cnpj;
        this.site = site;
        this.endereco = endereco;
        this.isActive = true;
    }
}
