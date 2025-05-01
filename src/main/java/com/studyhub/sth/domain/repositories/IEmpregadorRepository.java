package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.Empregador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEmpregadorRepository extends JpaRepository<Empregador, UUID> {
}
