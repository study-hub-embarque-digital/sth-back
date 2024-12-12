package com.studyhub.sth.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "sessions")
@Table(name = "sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "sessionId")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID sessionId;

    private String ipAddress;
    private String device;
    private String os;
    private String browser;

    @OneToOne
    @JoinColumn(name = "key")
    private RefreshToken refreshToken;
}