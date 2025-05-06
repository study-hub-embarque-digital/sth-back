package com.studyhub.sth.domain.before.repositories;

import com.studyhub.sth.domain.before.entities.Empregador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEmpregadorRepository extends JpaRepository<Empregador, UUID> {
}
