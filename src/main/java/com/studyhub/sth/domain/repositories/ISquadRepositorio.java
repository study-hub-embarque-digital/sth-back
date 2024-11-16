package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.Squad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ISquadRepositorio extends JpaRepository<Squad, UUID> {
    Optional<Squad> findByNomeContainsIgnoreCase(String nome);

}

