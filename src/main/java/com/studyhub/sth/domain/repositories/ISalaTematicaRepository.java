package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.SalaTematica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ISalaTematicaRepository extends JpaRepository<SalaTematica, UUID> {
    List<SalaTematica> findByRoomRoomId(UUID roomId);
}
