package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
}
