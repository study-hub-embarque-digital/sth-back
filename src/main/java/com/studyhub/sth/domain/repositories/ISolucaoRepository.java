package com.studyhub.sth.domain.repositories;
import com.studyhub.sth.domain.entities.Solucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository

public interface ISolucaoRepository extends JpaRepository<Solucao, UUID> {
}

