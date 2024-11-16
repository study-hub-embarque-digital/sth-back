package com.studyhub.sth.domain.entities;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(columnDefinition = "text")
    private String conteudo;

    @ManyToOne
    private Usuario autor;

    @ManyToMany
    @JoinTable(
            name = "artigo_tag",
            joinColumns = @JoinColumn(name = "artigo_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
}