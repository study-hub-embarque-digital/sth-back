package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.Mentoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IMentoriaRepository extends JpaRepository<Mentoria, UUID> {
}
