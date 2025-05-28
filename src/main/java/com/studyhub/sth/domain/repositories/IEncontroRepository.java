package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.Encontro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface IEncontroRepository extends JpaRepository<Encontro, UUID> {
    Optional<Encontro> findByMentoria_Squad_SquadIdAndData(UUID squadId, LocalDate data);

}
