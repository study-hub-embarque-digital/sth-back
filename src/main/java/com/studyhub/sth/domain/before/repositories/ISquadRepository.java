package com.studyhub.sth.domain.before.repositories;

import com.studyhub.sth.domain.squads.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ISquadRepository extends JpaRepository<Squad, UUID> {
    Optional<Squad> findByNomeContainsIgnoreCase(String nome);
}

