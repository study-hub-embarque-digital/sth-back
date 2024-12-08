package com.studyhub.sth.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "discussoes")
@Table(name = "discussoes")
@EqualsAndHashCode(of = "discussaoId")
public class Discussao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID discussaoId;
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @ManyToOne
    private Discussao discussaoPai;

    private Date criadoEm;
    private Date atualizadoEm;

    @PrePersist
    public void onPersist()
    {
        Date d = new Date();
        this.setCriadoEm(d);
        this.setAtualizadoEm(d);
    }

    @PreUpdate
    public void onUpdate()
    {
        this.setAtualizadoEm(new Date());
    }
}
