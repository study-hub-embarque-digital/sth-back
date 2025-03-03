package com.studyhub.sth.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "admins")
@Table(name = "admins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "adminId")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID adminId;

    @OneToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
