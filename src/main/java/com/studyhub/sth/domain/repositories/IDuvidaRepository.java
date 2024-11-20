package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.Duvida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDuvidaRepository extends JpaRepository<Duvida, UUID> {
}
