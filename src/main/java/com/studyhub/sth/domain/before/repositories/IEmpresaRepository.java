package com.studyhub.sth.domain.before.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.studyhub.sth.domain.before.entities.Empresa;

import java.util.List;
import java.util.UUID;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, UUID> {
    List<Empresa> findByNomeFantasiaContaining(String nomeFantasia);

}