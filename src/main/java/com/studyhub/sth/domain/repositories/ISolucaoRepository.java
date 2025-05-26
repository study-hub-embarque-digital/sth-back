package com.studyhub.sth.domain.repositories;
import com.studyhub.sth.domain.entities.Solucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository

public interface ISolucaoRepository extends JpaRepository<Solucao, UUID> {
    @Query(value = "SELECT COUNT(*) FROM solucoes WHERE duvida_id = :duvidaId", nativeQuery = true)
    long contarSolucoesPorDuvida(@Param("duvidaId") UUID duvidaId);
}
