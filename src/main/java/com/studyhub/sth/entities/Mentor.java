package com.studyhub.sth.entities;

import com.studyhub.sth.dtos.mentor.NovoMentorDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

//    @OneToMany(mappedBy = "mentor")
//    private List<Squad> squads;

}
