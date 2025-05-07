package com.studyhub.sth.domain.repositories;


import com.studyhub.sth.domain.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEnderecoRepository extends JpaRepository<Endereco, UUID> {
}
