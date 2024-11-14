package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRepresentanteRepositorio extends JpaRepository<Representante, UUID> {
    // Método extra
    //List<Representante> findByUsuario_Email(String email);

    //Representante criarRepresentante(NovoRepresentanteDto dto);
}
