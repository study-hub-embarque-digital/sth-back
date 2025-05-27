package com.studyhub.sth.domain.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.studyhub.sth.domain.enums.TipoVinculo;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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

    @OneToMany(mappedBy = "empregador")
    private List<Job> jobs = new ArrayList<>();
}
