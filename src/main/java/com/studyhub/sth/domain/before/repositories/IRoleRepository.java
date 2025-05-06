package com.studyhub.sth.domain.before.repositories;

import com.studyhub.sth.domain.before.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID> {
    List<Role> findByNome(String nome);
}
