package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.SalaTematica;
import com.studyhub.sth.domain.enums.Dificuldade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ISalaTematicaRepository extends JpaRepository<SalaTematica, UUID> {
    @Query("SELECT st FROM SalaTematica st " +
            "WHERE st.room.roomId IS NOT NULL " +
            "AND st.room.roomId = :roomId " +
            "AND st.topico.dificuldade = :difficulty")
    List<SalaTematica> findByRoomAndDifficulty(UUID roomId, Dificuldade difficulty);
}
