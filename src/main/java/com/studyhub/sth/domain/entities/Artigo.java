package com.studyhub.sth.domain.entities;

import com.studyhub.sth.application.dtos.tag.TagDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Artigo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToMany
    @JoinTable(
            name = "artigos_tags",
            joinColumns = @JoinColumn(name = "artigo_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

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
}