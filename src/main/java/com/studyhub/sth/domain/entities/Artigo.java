package com.studyhub.sth.domain.entities;

import com.studyhub.sth.application.dtos.tag.TagDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
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

    @ManyToMany()
    @JoinTable(
            name = "artigos_tags",
            joinColumns = @JoinColumn(name = "artigo_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    public Artigo(String titulo, String conteudo, Usuario autor) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.autor = autor;
        this.tags = new ArrayList<>();
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void addRangeTags(List<Tag> tags) {
        this.tags.addAll(tags);
    }
}