package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.ApresentacaoReuniao;
import com.studyhub.sth.domain.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IApresentacaoReuniaoRepository extends JpaRepository<ApresentacaoReuniao, UUID> {
//    @Query("SELECT ar.topicos FROM ApresentacaoReuniao ar WHERE ar.usuario.usuarioId = :usuarioId AND ar.reuniao.reuniaoId = :reuniaoId")
//    List<Topico> findTopicosByReuniaoAndUser(UUID reuniaoId, UUID usuarioId);
    List<ApresentacaoReuniao> findApresentacaoReuniaoByReuniaoReuniaoId(UUID reuniaoId);
}
