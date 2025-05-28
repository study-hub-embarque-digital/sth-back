package com.studyhub.sth.domain.repositories;


import com.studyhub.sth.application.dtos.graficos.EmpresaPorCicloDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.studyhub.sth.domain.entities.Empresa;

import java.util.List;
import java.util.UUID;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, UUID> {
    List<Empresa> findByNomeFantasiaContaining(String nomeFantasia);

    @Query("SELECT s.ciclo AS ciclo, COUNT(DISTINCT e.empresaId) AS totalEmpresas " +
            "FROM empresas e " +
            "JOIN e.squads s " +
            "GROUP BY s.ciclo " +
            "ORDER BY s.ciclo")
    List<EmpresaPorCicloDTO> findEmpresasPorCiclo();
}