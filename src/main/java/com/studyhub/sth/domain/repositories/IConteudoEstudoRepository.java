package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.ConteudoEstudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IConteudoEstudoRepository extends JpaRepository<ConteudoEstudo, UUID> {
    // List<ConteudoEstudo> findByRoom_RoomId(UUID roomId);
}
