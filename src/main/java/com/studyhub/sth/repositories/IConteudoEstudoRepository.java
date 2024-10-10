package com.studyhub.sth.repositories;

import com.studyhub.sth.entities.ConteudoEstudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IConteudoEstudoRepository extends JpaRepository<ConteudoEstudo, UUID> {
    List<ConteudoEstudo> findByRoom_Id(UUID roomId);
}
