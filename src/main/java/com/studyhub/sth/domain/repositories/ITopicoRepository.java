package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.Topico;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ITopicoRepository extends JpaRepository<Topico, UUID> {
    @Query("SELECT t FROM Topico t WHERE t.topicoPai IS NOT NULL AND t.topicoPai.topicoId = :topicoId")
    List<Topico> findSubtopicos(UUID topicoId);
}
