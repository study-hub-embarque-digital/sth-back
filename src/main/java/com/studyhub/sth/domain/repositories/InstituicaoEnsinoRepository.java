package com.studyhub.sth.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.studyhub.sth.domain.entities.InstituicaoEnsino;

import java.util.List;
import java.util.UUID;

@Repository
public interface InstituicaoEnsinoRepository extends JpaRepository<InstituicaoEnsino, UUID> {
//    List<InstituicaoEnsino> findByNomeContaining(String nome);
    
}
