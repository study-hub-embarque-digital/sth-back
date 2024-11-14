package com.studyhub.sth.domain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.studyhub.sth.domain.entities.Empresa;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {
    List<Empresa> findByNomeFantasiaContaining(String nomeFantasia);

}