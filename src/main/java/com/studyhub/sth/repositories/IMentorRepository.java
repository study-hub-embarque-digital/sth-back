package com.studyhub.sth.repositories;

import com.studyhub.sth.entities.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMentorRepository extends JpaRepository<Mentor, UUID> {

}
