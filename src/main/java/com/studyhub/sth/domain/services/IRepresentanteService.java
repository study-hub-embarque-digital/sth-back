package com.studyhub.sth.domain.services;

import com.studyhub.sth.application.dtos.representante.RepresentanteCreateDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteUpdateDto;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;

import java.util.List;
import java.util.UUID;

public interface IRepresentanteService {
    public String criarRepresentante(RepresentanteCreateDto dto) throws Exception;
    public List<RepresentanteDto> listarRepresentantes();
    public RepresentanteDto obterRepresentantePorId(UUID id);
    public RepresentanteDto atualizarRepresentante(UUID id, RepresentanteUpdateDto dto);
    public void deletarRepresentante(UUID id);
}
