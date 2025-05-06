package com.studyhub.sth.domain.before.repositories;

import com.studyhub.sth.domain.before.entities.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IReuniaoRepository extends JpaRepository<Reuniao, UUID> {
    @Query("SELECT r FROM Reuniao r WHERE (r.status = 0 OR r.status = 1) AND r.salaTematica.salaTematicaId = :salaTematicaId AND r.tipo = 0 ORDER BY r.criadoEm")
    List<Reuniao> findAvailableReunioes(UUID salaTematicaId);
}
