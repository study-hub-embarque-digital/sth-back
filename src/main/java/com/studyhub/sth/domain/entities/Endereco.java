package com.studyhub.sth.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "enderecos")
@Table(name = "enderecos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public Endereco(double lagitude, double longitude, String cep, String logradouro, String cidade, String estado, String complemento, String numero) {
        this.lagitude = lagitude;
        this.longitude = longitude;
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
        this.numero = numero;
    }
}