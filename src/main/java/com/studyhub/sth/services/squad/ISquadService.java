package com.studyhub.sth.services.squad;

import com.studyhub.sth.dtos.squad.SquadCreateDTO;
import com.studyhub.sth.dtos.squad.SquadDTO;
import com.studyhub.sth.dtos.squad.SquadUpdateDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISquadService {

    List<SquadDTO> findAll();

    Optional<SquadDTO> findById(UUID id);

    SquadDTO save(SquadCreateDTO squadCreateDTO);

    void deleteById(UUID id);

    Optional<SquadDTO> update(UUID id, SquadUpdateDTO squadUpdateDTO);
}

