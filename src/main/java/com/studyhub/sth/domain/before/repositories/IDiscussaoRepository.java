package com.studyhub.sth.domain.before.repositories;

import com.studyhub.sth.domain.before.entities.Discussao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IDiscussaoRepository extends JpaRepository<Discussao, UUID> {
    List<Discussao> findAllByDiscussaoPai(Discussao discussao);
}
