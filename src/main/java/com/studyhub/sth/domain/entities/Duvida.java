package com.studyhub.sth.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "duvidas")
@Table(name = "duvidas")
@EqualsAndHashCode(of = "duvidaId")
public class Duvida {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID duvidaId;
    private String titulo;
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "duvidas_tags",
            joinColumns = @JoinColumn(name = "duvida_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "duvida")
    private List<Solucao> solucoes;

    private Date criadoEm;
    private Date atualizadoEm;

    @ManyToOne
    @JoinColumn(name = "atualizado_por")
    private Usuario atualizadoPor;

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

    private boolean resolvida = false;
}
