package com.studyhub.sth.repositories;

import com.studyhub.sth.entities.Squad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ISquadRepositorio extends JpaRepository<Squad, UUID> {
    Optional<Squad> findBySquadNomeContainsIgnoreCase(String nome);

}

