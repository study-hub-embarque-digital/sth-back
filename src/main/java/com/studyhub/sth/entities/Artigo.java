package com.studyhub.sth.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "artigos")
@Table(name = "artigos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "artigoId")
public class Artigo {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID artigoId;

    private String titulo;
    private String conteudo;
    private String autor;

    @ManyToMany
    @JoinTable(
        name = "artigo_tags", 
        joinColumns = @JoinColumn(name = "artigo_id"), 
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
}
