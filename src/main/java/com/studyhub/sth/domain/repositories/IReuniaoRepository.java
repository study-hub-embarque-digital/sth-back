package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IReuniaoRepository extends JpaRepository<Reuniao, UUID> {
    Optional<Reuniao> findBySalaTematicaSalaTematicaId(UUID salaTematicaId);
}
