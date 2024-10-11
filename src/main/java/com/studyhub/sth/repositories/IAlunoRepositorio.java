package com.studyhub.sth.repositories;

import com.studyhub.sth.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAlunoRepositorio extends JpaRepository<Aluno, UUID> {
    List<Aluno> findAlunosByPeriodo(int periodo);
}
