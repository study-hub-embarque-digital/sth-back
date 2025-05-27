package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.graficos.EmpresaSquadCountDto;
import com.studyhub.sth.application.dtos.graficos.SquadsDemodayPorInstituicaoDTO;
import com.studyhub.sth.application.dtos.squad.SquadCreateDTO;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.application.dtos.squad.SquadListDTO;
import com.studyhub.sth.application.dtos.squad.SquadUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface ISquadService {

    List<SquadDTO> findAll();

    SquadListDTO findById(UUID id);

    SquadDTO save(SquadCreateDTO squadCreateDTO);

    void deleteById(UUID id);

    SquadDTO update(UUID id, SquadUpdateDTO squadUpdateDTO);

    SquadDTO findBySquadNomeContainsIgnoreCase(String nome);

    List<EmpresaSquadCountDto> countSquadsPorEmpresa();

    List<SquadsDemodayPorInstituicaoDTO> findSquadsSelecionadosPorInstituicao();
}

