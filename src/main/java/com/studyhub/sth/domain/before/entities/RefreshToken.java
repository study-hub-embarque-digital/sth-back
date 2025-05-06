package com.studyhub.sth.domain.before.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "refresh_tokens")
@Table(name = "refresh_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "key")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID key;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Instant expiration;
}
