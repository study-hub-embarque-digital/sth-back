package com.studyhub.sth.repositories;

import com.studyhub.sth.dtos.tag.TagDto;
import com.studyhub.sth.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITagRepository extends JpaRepository<Tag,UUID> {
    Optional<TagDto> findByNomeContainsIgnoreCase(String nome);
}
