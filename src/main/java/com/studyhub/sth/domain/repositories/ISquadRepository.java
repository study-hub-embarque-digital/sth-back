package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.application.dtos.graficos.EmpresaSquadCountDto;
import com.studyhub.sth.application.dtos.graficos.SquadsDemodayPorInstituicaoDTO;
import com.studyhub.sth.domain.entities.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ISquadRepository extends JpaRepository<Squad, UUID> {
    Optional<Squad> findByNomeContainsIgnoreCase(String nome);

    @Query("SELECT s.empresa.nomeFantasia AS nomeEmpresa, COUNT(s) AS quantidadeSquads FROM squads s GROUP BY s.empresa.nomeFantasia")
    List<EmpresaSquadCountDto> countSquadsPorEmpresa();

    @Query("SELECT s.instituicaoEnsino.nomeFantasia AS nomeFantasia, COUNT(s) AS totalSquads " +
            "FROM squads s " +
            "WHERE s.selecionadoParaDemoDay = true " +
            "GROUP BY s.instituicaoEnsino.nomeFantasia " +
            "ORDER BY totalSquads DESC")
    List<SquadsDemodayPorInstituicaoDTO> findSquadsSelecionadosPorInstituicao();
}

