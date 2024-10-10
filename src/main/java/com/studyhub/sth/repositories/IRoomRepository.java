package com.studyhub.sth.repositories;

import com.studyhub.sth.entities.ConteudoEstudo;
import com.studyhub.sth.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoomRepository extends JpaRepository<Room, UUID> {
    @Query("SELECT c FROM ConteudoEstudo c WHERE c.room.roomId = :roomId AND c.conteudoEstudoId = :conteudoEstudoId")
    Optional<ConteudoEstudo> findConteudoEstudoByRoomIdAndConteudoEstudoId(UUID roomId, UUID conteudoEstudoId);
}


