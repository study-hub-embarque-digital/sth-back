package com.studyhub.sth.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tags")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "tagId")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tagId;

    private String nome;

}
