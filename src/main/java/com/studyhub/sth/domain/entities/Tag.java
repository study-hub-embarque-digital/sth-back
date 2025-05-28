package com.studyhub.sth.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "tags")
@Table(name = "tags")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "nome")
public class Tag {
    @Id
    private String nome;

    public Tag(String nome) {
        this.nome = nome;
    }
}
