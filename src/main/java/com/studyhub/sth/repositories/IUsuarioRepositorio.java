package com.studyhub.sth.repositories;

import com.studyhub.sth.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, UUID> {
}
