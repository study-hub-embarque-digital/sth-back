package com.studyhub.sth.repositories;

import com.studyhub.sth.dtos.representante.NovoRepresentanteDto;
import com.studyhub.sth.entities.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IRepresentanteRepositorio extends JpaRepository<Representante, UUID> {
    // Método extra
    List<Representante> findByUsuario_Email(String email);

    Representante criarRepresentante(NovoRepresentanteDto dto);
}
