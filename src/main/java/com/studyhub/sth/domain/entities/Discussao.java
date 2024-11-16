package com.studyhub.sth.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "discussoes")
@Table(name = "discussoes")
@EqualsAndHashCode(of = "discussaoId")
public class Discussao {
    private UUID discussaoId;
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @ManyToOne
    private Discussao discussaoPai;
}
