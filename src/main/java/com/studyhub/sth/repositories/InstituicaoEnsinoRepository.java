package com.studyhub.sth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studyhub.sth.entities.InstituicaoEnsino;

import java.util.UUID;

public interface InstituicaoEnsinoRepository extends JpaRepository<InstituicaoEnsino, UUID> {
}
