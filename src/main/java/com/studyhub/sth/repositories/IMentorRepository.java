package com.studyhub.sth.repositories;

import com.studyhub.sth.entities.Mentor;
//import com.studyhub.sth.entities.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IMentorRepository extends JpaRepository<Mentor, UUID> {
//
//    @Query("SELECT s FROM Squad s WHERE s.mentor.id = :mentorId")
//    List<Squad> getAllSquad(UUID mentorId);

}
