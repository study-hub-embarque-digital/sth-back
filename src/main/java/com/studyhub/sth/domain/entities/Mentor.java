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
@EqualsAndHashCode(of = "mentorId")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID mentorId;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "mentor")
    private List<Squad> squads;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}
