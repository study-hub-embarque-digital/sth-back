package com.studyhub.sth.domain.before.entities;


import com.studyhub.sth.domain.before.enums.TipoVinculo;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "empregador")
@Table(name = "empregadores")
@Getter
@Setter
@EqualsAndHashCode(of = "empregadorId")
public class Empregador {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID empregadorId;

    private String nomeGestor;
    private String emailGestor;
    private String cargoGestor;

    private String nomeEmpresa;
    private String cnpjEmpresa;


    @Enumerated(EnumType.STRING)
    private TipoVinculo tipoVinculo;

    private String cargoDetalhado;
    private String atividadesDesenvolvidas;

    @OneToMany(mappedBy = "empregador")
    private List<Job> jobs = new ArrayList<>();
}
