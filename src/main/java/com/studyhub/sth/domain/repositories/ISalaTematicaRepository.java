package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.SalaTematica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ISalaTematicaRepository extends JpaRepository<SalaTematica, UUID> {
}
