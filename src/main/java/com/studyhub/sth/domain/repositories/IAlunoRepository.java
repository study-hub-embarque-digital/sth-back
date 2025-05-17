package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.Aluno;
import com.studyhub.sth.domain.enums.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, UUID> {
    List<Aluno> findAlunosByPeriodo(Periodo periodo);
}
