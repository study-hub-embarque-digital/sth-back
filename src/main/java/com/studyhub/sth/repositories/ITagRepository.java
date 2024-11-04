package com.studyhub.sth.repositories;

import com.studyhub.sth.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITagRepository extends JpaRepository<Tag,UUID> {
}
