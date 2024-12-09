package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.Duvida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDuvidaRepository extends JpaRepository<Duvida, UUID> {
}
