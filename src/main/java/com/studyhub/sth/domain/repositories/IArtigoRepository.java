package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IArtigoRepository extends JpaRepository<Artigo, UUID> {
    List<Artigo> findByTituloContainsIgnoreCase(String titulo);

    @Query("SELECT a FROM Artigo a JOIN a.tags t WHERE t.id = :tagId")
    List<Artigo> findByTagId(@Param("tagId") UUID tagId);

    List<Artigo> findByAutorUsuarioId(UUID usuarioId);
}
