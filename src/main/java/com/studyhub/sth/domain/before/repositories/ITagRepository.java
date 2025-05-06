package com.studyhub.sth.domain.before.repositories;

import com.studyhub.sth.application.dtos.tag.TagDto;
import com.studyhub.sth.domain.before.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITagRepository extends JpaRepository<Tag,UUID> {
    Optional<TagDto> findByNomeContainsIgnoreCase(String nome);
}
