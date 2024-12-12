package com.studyhub.sth.domain.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "enderecos")
@Table(name = "enderecos")
@Getter
@Setter
@EqualsAndHashCode(of = "enderecoId")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID enderecoId;

    private double lagitude;
    private double longitude;

    private String cep;
    private String logradouro;
    private String cidade;
    private String estado;
    private String complemento;
    private String numero;
}