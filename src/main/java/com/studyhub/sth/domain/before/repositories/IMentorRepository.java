package com.studyhub.sth.domain.before.repositories;

import com.studyhub.sth.domain.before.entities.Mentor;
//import com.studyhub.sth.entities.Squad;
import com.studyhub.sth.domain.squads.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IMentorRepository extends JpaRepository<Mentor, UUID> {
    Optional<Mentor> findByUsuarioNomeContainsIgnoreCase(String nome);

    @Query("SELECT s FROM squads s WHERE s.mentor.mentorId = :mentorId")
    List<Squad> getAllSquad(UUID mentorId);
}
