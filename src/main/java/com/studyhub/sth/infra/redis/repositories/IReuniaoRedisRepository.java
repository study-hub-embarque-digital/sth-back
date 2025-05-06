package com.studyhub.sth.infra.redis.repositories;

import com.studyhub.sth.infra.redis.entities.ReuniaoCache;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IReuniaoRedisRepository extends CrudRepository<ReuniaoCache, UUID> {
}
