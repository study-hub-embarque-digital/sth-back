package com.studyhub.sth.infra.redis.repositories;

import com.studyhub.sth.infra.redis.entities.ApresentacaoReuniaoCache;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IApresencaoReuniaoCacheRepository extends CrudRepository<ApresentacaoReuniaoCache, UUID> {
}
