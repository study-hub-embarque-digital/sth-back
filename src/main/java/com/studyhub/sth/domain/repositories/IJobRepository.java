package com.studyhub.sth.domain.repositories;

import com.studyhub.sth.domain.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IJobRepository extends JpaRepository<Job, UUID> {
}
